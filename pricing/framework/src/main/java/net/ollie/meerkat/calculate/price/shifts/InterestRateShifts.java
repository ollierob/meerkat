package net.ollie.meerkat.calculate.price.shifts;

import javax.annotation.Nonnull;

import net.ollie.meerkat.numeric.interest.FixedInterestRate;

/**
 *
 * @author Ollie
 */
public interface InterestRateShifts extends SecurityShifts {

    @Nonnull
    FixedInterestRate shiftRates(@Nonnull FixedInterestRate rate);

    interface InterestRateShifter {

        default FixedInterestRate shift(final FixedInterestRate rate, final InterestRateShifts shifts) {
            return shifts.shiftRates(rate);
        }

    }

}
