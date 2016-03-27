package net.ollie.risk.risk.pricing.bond;

import java.time.LocalDate;
import java.util.function.BiFunction;
import java.util.function.Function;

import net.ollie.meerkat.calculate.fx.ExchangeRateCalculator;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondPricer;
import net.ollie.meerkat.calculate.price.bond.BondShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.interest.FixedInterestRate;
import net.ollie.meerkat.numeric.interest.InterestRate;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.FixedCouponBond;
import net.ollie.meerkat.security.bond.FixedCouponBond.FixedCouponBondCoupons;
import net.ollie.meerkat.security.bond.StraightBond.StraightBondNominal;
import net.ollie.meerkat.security.bond.coupon.FixedCoupon;

/**
 *
 * @author ollie
 */
public class FixedCouponBondPricer implements BondPricer<FixedCouponBond> {

    private final Function<LocalDate, ExchangeRateCalculator> fxRates;
    private final BiFunction<LocalDate, CurrencyId, InterestRate> discountRates;

    public FixedCouponBondPricer(
            final Function<LocalDate, ExchangeRateCalculator> exchangeRates,
            final BiFunction<LocalDate, CurrencyId, InterestRate> discountRates) {
        this.fxRates = exchangeRates;
        this.discountRates = discountRates;
    }

    @Override
    public <C extends CurrencyId> BondPrice<C> price(
            final LocalDate date,
            final FixedCouponBond bond,
            final BondShifts shifts,
            final C currency) {

        final ExchangeRateCalculator fxRates = this.fxRates.apply(date);
        final FixedInterestRate accrualRate = this.shift(bond.couponRate(), shifts);
        final InterestRate discountRate = discountRates.apply(date, currency);

        final StraightBondNominal nominal = bond.nominal();
        final Money<C> par = this.shift(nominal.par(), shifts, currency, fxRates);

        //Prices
        final LocalDate maturity = bond.dates().matures();
        final FixedCouponBondCoupons coupons = bond.coupons();
        final Money<C> clean = this.cleanValue(date, maturity, par, coupons, accrualRate, discountRate, fxRates, shifts);
        final Money<C> dirty = this.dirtyValue(date, coupons, clean, accrualRate, fxRates, shifts);

        return new GenericBondPrice<>(par, clean, dirty);

    }

    private <C extends CurrencyId> Money<C> cleanValue(
            final LocalDate date,
            final LocalDate maturity,
            final Money<C> par,
            final FixedCouponBondCoupons coupons,
            final InterestRate accrualRate,
            final InterestRate discountRate,
            final ExchangeRateCalculator fxRates,
            final BondShifts shifts) {

        final C currency = par.currencyId();

        Money<C> cleanAmount = par;
        for (final FixedCoupon coupon : coupons.onOrAfter(date)) {
            final Money<C> amount = this.shift(coupon.amount(), shifts, currency, fxRates);
            cleanAmount = cleanAmount.plus(accrualRate.accrue(amount, coupon.date(), maturity));
        }

        return discountRate.discount(cleanAmount, date, maturity);

    }

    private <C extends Object & CurrencyId> Money<C> dirtyValue(
            final LocalDate date,
            final FixedCouponBondCoupons coupons,
            final Money<C> cleanValue,
            final InterestRate accrualRate,
            final ExchangeRateCalculator fxRates,
            final BondShifts shifts) {

        final FixedCoupon prior = coupons.prior(date);
        if (prior == null) {
            return cleanValue;
        }

        final Money<C> priorAmount = this.shift(prior.amount(), shifts, cleanValue.currencyId(), fxRates);
        final Money<C> accruedAmount = accrualRate.accrue(priorAmount, prior.date(), date);

        return cleanValue.plus(accruedAmount);

    }

}
