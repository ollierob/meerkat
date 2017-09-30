package net.meerkat.pricing.shifts.interest;

import javax.annotation.Nonnull;

import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.ollie.goat.numeric.percentage.Percentage;
import net.meerkat.pricing.shifts.InstrumentShifts;

/**
 *
 * @author Ollie
 */
public interface InterestRateShifts extends InstrumentShifts {

    @Nonnull
    InterestRate shift(@Nonnull InterestRate rate);

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

    static InterestRateShifts cast(final InstrumentShifts shifts) {
        return shifts.as(InterestRateShifts.class).orElseGet(InterestRateShifts::none);
    }

    interface InterestRateShifter {

        default InterestRate shift(final InterestRate rate, final InterestRateShifts shifts) {
            return shifts.shift(rate);
        }

        default FixedInterestRate shift(final FixedInterestRate rate, final InterestRateShifts shifts) {
            return (FixedInterestRate) shifts.shift(rate);
        }

    }

}
