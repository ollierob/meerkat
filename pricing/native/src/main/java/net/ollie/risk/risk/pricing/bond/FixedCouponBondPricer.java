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
import net.ollie.risk.risk.pricing.AnnuityPricer;

/**
 *
 * @author ollie
 */
public class FixedCouponBondPricer extends AnnuityPricer implements BondPricer<LocalDate, FixedCouponBond> {

    private final Function<LocalDate, ExchangeRateCalculator> exchangeRates;
    private final BiFunction<LocalDate, CurrencyId, InterestRate> discountRates;

    public FixedCouponBondPricer(
            final Function<LocalDate, ExchangeRateCalculator> exchangeRates,
            final BiFunction<LocalDate, CurrencyId, InterestRate> discountRates) {
        this.exchangeRates = exchangeRates;
        this.discountRates = discountRates;
    }

    @Override
    public <C extends CurrencyId> BondPrice<C> price(
            final LocalDate date,
            final FixedCouponBond bond,
            final BondShifts shifts,
            final C currency) {

        final StraightBondNominal nominal = bond.nominal();
        final FixedCouponBondCoupons coupons = bond.coupons();
        final LocalDate maturity = bond.dates().matures();
        final Money<C> par = this.shift(nominal.par(), shifts, currency, exchangeRates.apply(date));
        final FixedInterestRate accrualRate = this.shift(bond.couponRate(), shifts);

        //Clean price
        final Money<C> cleanPrice = this.cleanPrice(date, maturity, par, coupons, accrualRate, shifts);
        final Money<C> dirtyPrice = this.dirtyPrice(date, coupons, cleanPrice, accrualRate, shifts);

        return new GenericBondPrice<>(par, cleanPrice, dirtyPrice);

    }

    private <C extends CurrencyId> Money<C> cleanPrice(
            final LocalDate date,
            final LocalDate maturity,
            final Money<C> par,
            final FixedCouponBondCoupons coupons,
            final InterestRate accrualRate,
            final BondShifts shifts) {

        final C currency = par.currencyId();
        final ExchangeRateCalculator fxRates = exchangeRates.apply(date);

        Money<C> cleanPrice = par;
        for (final FixedCoupon coupon : coupons.onOrAfter(date)) {
            final Money<C> amount = this.shift(coupon.amount(), shifts, currency, fxRates);
            cleanPrice = cleanPrice.plus(accrualRate.accrue(amount, coupon.date(), maturity));
        }

        return cleanPrice;

    }

    private <C extends Object & CurrencyId> Money<C> dirtyPrice(
            final LocalDate date,
            final FixedCouponBondCoupons coupons,
            final Money<C> cleanPrice,
            final InterestRate accrualRate,
            final BondShifts shifts) {

        final FixedCoupon prior = coupons.prior(date);
        if (prior == null) {
            return cleanPrice;
        }

        final ExchangeRateCalculator fxRates = exchangeRates.apply(date);

        final Money<C> couponAmount = this.shift(prior.amount(), shifts, cleanPrice.currencyId(), fxRates);
        final Money<C> accrued = accrualRate.accrue(couponAmount, prior.date(), date);

        return cleanPrice.plus(accrued);

    }

    @Override
    protected InterestRate discountRate(final LocalDate date, final CurrencyId currency) {
        return discountRates.apply(date, currency);
    }

}
