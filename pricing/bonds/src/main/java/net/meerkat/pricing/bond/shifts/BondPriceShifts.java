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
public interface BondPriceShifts extends InterestRateShifts, ExchangeRateShifts {

    static BondPriceShifts none() {
        return NoBondPriceShifts.INSTANCE;
    }

    static BondPriceShifts absoluteYield(final Percentage shift) {
        return shift.isZero()
                ? none()
                : cast(InterestRateShifts.absolute(shift));
    }

    static BondPriceShifts relativePrice(@Nonnull final Percentage percentage) {
        return percentage.isZero() ? none() : NoBondPriceShifts.INSTANCE;
    }

    static BondPriceShifts cast(final InstrumentPriceShifts shifts) {
        return shifts instanceof BondPriceShifts
                ? ((BondPriceShifts) shifts)
                : new WrappedBondPriceShifts(shifts);
    }

}
