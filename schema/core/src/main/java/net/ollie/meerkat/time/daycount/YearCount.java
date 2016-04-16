package net.ollie.meerkat.time.daycount;

import java.time.LocalDate;
import java.util.Optional;

import javax.annotation.Nonnull;

import net.ollie.meerkat.time.interim.Interim;
import net.ollie.meerkat.time.interim.Interval;
import net.ollie.meerkat.utils.time.Years;

/**
 *
 * @author Ollie
 */
public interface YearCount {

    @Nonnull
    Years yearsBetween(@Nonnull LocalDate startInclusive, @Nonnull LocalDate endInclusive);

    default Years yearsIn(@Nonnull final Interval interval) {
        return this.yearsBetween(interval.startInclusive(), interval.endInclusive());
    }

    default Optional<Years> yearsIn(@Nonnull final Interim interim) {
        return interim.closed().map(this::yearsIn);
    }

}
