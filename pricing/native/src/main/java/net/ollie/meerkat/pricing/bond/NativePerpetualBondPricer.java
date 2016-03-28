package net.ollie.meerkat.pricing.bond;

import java.time.LocalDate;
import java.util.function.Function;

import net.ollie.meerkat.calculate.fx.ExchangeRateCalculator;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.interest.FixedInterestRate;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.PerpetualBond;
import net.ollie.meerkat.security.bond.coupon.FixedCoupon;
import net.ollie.meerkat.calculate.price.bond.BondTypePricer;

/**
 *
 * @author Ollie
 */
public class NativePerpetualBondPricer implements BondTypePricer<PerpetualBond> {

    private final Function<LocalDate, ExchangeRateCalculator> exchangeRates;

    public NativePerpetualBondPricer(final Function<LocalDate, ExchangeRateCalculator> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    @Override
    public <C extends CurrencyId> BondPrice<C> price(
            final LocalDate date,
            final PerpetualBond bond,
            final BondShifts shifts,
            final C currency) {

        final ExchangeRateCalculator calculator = exchangeRates.apply(date);

        //Coupons
        final PerpetualBond.PerpetualBondCoupons coupons = bond.coupons();
        final Money<C> amount = this.shiftFx(coupons.recurringAmount(), shifts, currency, calculator);
        final FixedInterestRate rate = this.shift(coupons.recurringRate(), shifts);
        final FixedCoupon prior = coupons.prior(date);

        //Prices
        final Money<C> par = this.shiftFx(bond.nominal().par(), shifts, currency, calculator);
        final Money<C> cleanPrice = amount.over(rate.annualRate());
        final Money<C> dirtyPrice = cleanPrice.plus(prior == null ? Money.zero(currency) : rate.accrue(amount, prior.date(), date));
        return new GenericBondPrice<>(par, cleanPrice, dirtyPrice);

    }

}
