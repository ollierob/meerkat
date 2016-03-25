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
import net.ollie.meerkat.security.bond.PerpetualBond;
import net.ollie.meerkat.security.bond.coupon.FixedCoupon;
import net.ollie.risk.risk.pricing.AnnuityPricer;

/**
 *
 * @author Ollie
 */
public class PerpetualBondPricer extends AnnuityPricer implements BondPricer<LocalDate, PerpetualBond> {

    private final Function<LocalDate, ExchangeRateCalculator> exchangeRates;
    private final BiFunction<LocalDate, CurrencyId, InterestRate> discountRates;

    public PerpetualBondPricer(
            final Function<LocalDate, ExchangeRateCalculator> exchangeRates,
            final BiFunction<LocalDate, CurrencyId, InterestRate> discountRates) {
        this.exchangeRates = exchangeRates;
        this.discountRates = discountRates;
    }

    @Override
    public <C extends CurrencyId> BondPrice<C> price(final LocalDate date, final PerpetualBond bond, final BondShifts shifts, final C currency) {

        final ExchangeRateCalculator calculator = exchangeRates.apply(date);

        //Coupons
        final PerpetualBond.PerpetualBondCoupons coupons = bond.coupons();
        final Money<C> amount = this.shift(coupons.recurringAmount(), shifts, currency, calculator);
        final FixedInterestRate rate = this.shift(coupons.recurringRate(), shifts);
        final FixedCoupon prior = coupons.prior(date);

        //Prices
        final Money<C> par = this.shift(bond.nominal().par(), shifts, currency, calculator);
        final Money<C> cleanPrice = amount.over(rate.annualRate());
        final Money<C> dirtyPrice = cleanPrice.plus(prior == null ? Money.zero(currency) : rate.accrue(amount, prior.date(), date));
        return new GenericBondPrice<>(par, cleanPrice, dirtyPrice);

    }

    @Override
    protected InterestRate discountRate(final LocalDate date, final CurrencyId currency) {
        return discountRates.apply(date, currency);
    }

}
