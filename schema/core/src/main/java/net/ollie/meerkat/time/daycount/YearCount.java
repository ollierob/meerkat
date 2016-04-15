package net.ollie.meerkat.time.daycount;

import java.time.LocalDate;
import java.util.Optional;

import javax.annotation.Nonnull;

import net.ollie.meerkat.time.interim.Interim;
import net.ollie.meerkat.time.interim.Interval;

import org.apache.commons.math3.fraction.Fraction;

/**
 *
 * @author Ollie
 */
public interface YearCount {

    @Nonnull
    Fraction yearsBetween(@Nonnull LocalDate startInclusive, @Nonnull LocalDate endInclusive);

    default Fraction yearsIn(@Nonnull final Interval interval) {
        return this.yearsBetween(interval.startInclusive(), interval.endInclusive());
    }

    default Optional<Fraction> yearsIn(@Nonnull final Interim interim) {
        return interim.closed().map(this::yearsIn);
    }

}
