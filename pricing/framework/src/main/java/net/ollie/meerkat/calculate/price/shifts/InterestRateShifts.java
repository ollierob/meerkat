package net.ollie.meerkat.calculate.price.shifts;

import javax.annotation.Nonnull;

import net.ollie.meerkat.numeric.interest.FixedInterestRate;
import net.ollie.goat.money.interest.InterestRate;

/**
 *
 * @author Ollie
 */
public interface InterestRateShifts extends SecurityShifts {

    @Nonnull
    FixedInterestRate shift(@Nonnull FixedInterestRate rate);

    @Nonnull
    @Deprecated //TODO
    InterestRate shift(@Nonnull InterestRate rate);

    interface InterestRateShifter {

        default FixedInterestRate shift(final FixedInterestRate rate, final InterestRateShifts shifts) {
            return shifts.shift(rate);
        }

        default InterestRate shift(final InterestRate rate, final InterestRateShifts shifts) {
            return shifts.shift(rate);
        }

    }

}
