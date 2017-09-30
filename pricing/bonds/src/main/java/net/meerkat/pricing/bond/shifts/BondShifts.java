package net.meerkat.pricing.bond.shifts;

import javax.annotation.Nonnull;

import net.meerkat.pricing.shifts.fx.ExchangeRateShifts;
import net.meerkat.pricing.shifts.interest.InterestRateShifts;
import net.ollie.goat.numeric.percentage.Percentage;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;

/**
 *
 * @author ollie
 */
public interface BondShifts extends InterestRateShifts, ExchangeRateShifts {

    static BondShifts none() {
        return NoBondShifts.INSTANCE;
    }

    static BondShifts absoluteYield(final Percentage shift) {
        return cast(InterestRateShifts.absolute(shift));
    }

    static BondShifts relativePrice(@Nonnull final Percentage percentage) {
        return percentage.isZero() ? none() : NoBondShifts.INSTANCE;
    }

    static BondShifts cast(final InstrumentPriceShifts shifts) {
        return shifts instanceof BondShifts
                ? ((BondShifts) shifts)
                : new WrappedBondShifts(shifts);
    }

}
