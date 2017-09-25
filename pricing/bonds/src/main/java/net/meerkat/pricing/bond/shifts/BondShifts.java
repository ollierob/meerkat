package net.meerkat.pricing.bond.shifts;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.pricing.shifts.PriceShifts;
import net.meerkat.pricing.shifts.SecurityShifts;
import net.meerkat.pricing.shifts.fx.ExchangeRateShifts;
import net.meerkat.pricing.shifts.interest.InterestRateShifts;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public interface BondShifts extends PriceShifts, InterestRateShifts, ExchangeRateShifts {

    static BondShifts none() {
        return NoBondShifts.INSTANCE;
    }

    static BondShifts absoluteYield(final Percentage shift) {
        return cast(InterestRateShifts.absolute(shift));
    }

    static BondShifts relativePrice(@Nonnull final Percentage percentage) {
        return percentage.isZero() ? none() : new NoBondShifts() {

            @Override
            public <C extends CurrencyId> Money<C> shift(final Money<C> price) {
                return price.times(percentage);
            }

        };
    }

    static BondShifts cast(final SecurityShifts shifts) {
        return shifts instanceof BondShifts
                ? ((BondShifts) shifts)
                : new WrappedBondShifts(shifts);
    }

}
