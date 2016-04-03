package net.ollie.meerkat.numeric.interest;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.curve.InterestRateCurve;
import net.ollie.meerkat.numeric.interest.daycount.YearCount;

/**
 *
 * @author ollie
 */
public interface FixedInterestRate extends InterestRate, Comparable<FixedInterestRate> {

    @Nonnull
    Percentage annualRate();

    @Nonnull
    YearCount yearCount();

    default boolean isNegative() {
        return this.annualRate().isNegative();
    }

    @Override
    @Deprecated
    default Percentage fixing(final LocalDate date) {
        return this.annualRate();
    }

    @Override
    default FixedInterestRate plus(final Percentage bump) {
        return this.with(this.annualRate().plus(bump));
    }

    @Override
    default int compareTo(final FixedInterestRate that) {
        return this.annualRate().compareTo(that.annualRate());
    }

    FixedInterestRate with(Percentage rate);

    @Nonnull
    default InterestRateCurve toCurve() {
        return InterestRateCurve.flat(this.annualRate());
    }

}
