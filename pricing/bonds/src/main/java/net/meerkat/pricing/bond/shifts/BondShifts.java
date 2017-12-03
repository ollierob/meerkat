package net.meerkat.pricing.bond.shifts;

import net.meerkat.numeric.percentage.Percentage;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;
import net.meerkat.pricing.shifts.fx.ExchangeRateShifts;
import net.meerkat.pricing.shifts.interest.InterestRateShifts;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface BondShifts extends InterestRateShifts, ExchangeRateShifts {

    static BondShifts none() {
        return NoBondShifts.INSTANCE;
    }

    static BondShifts absoluteYield(final Percentage shift) {
        return shift.isZero()
                ? none()
                : cast(InterestRateShifts.absolute(shift));
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
