package net.meerkat.pricing.shifts.interest;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;
import net.ollie.goat.numeric.percentage.Percentage;
import net.meerkat.money.interest.InterestRateSnapshot;

/**
 *
 * @author Ollie
 */
public interface InterestRateShifts extends InstrumentPriceShifts {

    @Nonnull
    InterestRate shift(@Nonnull InterestRate rate);

    default FixedInterestRate shift(@Nonnull FixedInterestRate rate) {
        return (FixedInterestRate) this.shift((InterestRate) rate);
    }

    @Nonnull
    @CheckReturnValue
    default InterestRateSnapshot shift(@Nonnull final InterestRateSnapshot rates) {
        return new ShiftedInterestRates(rates, this);
    }

    static InterestRateShifts none() {
        return NoInterestRateShifts.INSTANCE;
    }

    static InterestRateShifts absolute(@Nonnull final Percentage shift) {
        return shift.isZero()
                ? none()
                : new AbsoluteInterestRateShifts(shift);
    }

    static InterestRateShifts relative(@Nonnull final Percentage shift) {
        return new RelativeInterestRateShifts(shift);
    }

    static InterestRateShifts cast(final InstrumentPriceShifts shifts) {
        return shifts.as(InterestRateShifts.class).orElseGet(InterestRateShifts::none);
    }

}
