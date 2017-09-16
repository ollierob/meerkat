package net.meerkat.pricing.shifts;

import javax.annotation.Nonnull;

import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.fixed.FixedInterestRate;

/**
 *
 * @author Ollie
 */
public interface InterestRateShifts extends SecurityShifts {

    @Nonnull
    InterestRate shift(@Nonnull InterestRate rate);

    static InterestRateShifts none() {
        return NoInterestRateShifts.INSTANCE;
    }

    static InterestRateShifts cast(final SecurityShifts shifts) {
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
