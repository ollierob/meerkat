package net.ollie.meerkat.numeric.interest.daycount;

import java.time.LocalDate;
import java.util.Optional;

import javax.annotation.Nonnull;

import org.apache.commons.math3.fraction.Fraction;

import net.ollie.meerkat.temporal.interim.Interim;
import net.ollie.meerkat.temporal.interim.Interval;

/**
 *
 * @author Ollie
 */
public interface YearCount {

    @Nonnull
    Fraction yearsBetween(@Nonnull LocalDate startInclusive, @Nonnull LocalDate endExclusive);

    default Fraction yearsIn(@Nonnull final Interval interval) {
        return this.yearsBetween(interval.startInclusive(), interval.endExclusive());
    }

    default Optional<Fraction> yearsIn(@Nonnull final Interim interim) {
        return interim.closed().map(this::yearsIn);
    }

}
