package net.meerkat.temporal.date.count;

import net.meerkat.temporal.date.years.Years;

import javax.annotation.Nonnull;
import java.time.LocalDate;

/**
 * @author Ollie
 */
public interface YearCount {

    @Nonnull
    Years yearsBetween(@Nonnull LocalDate startInclusive, @Nonnull LocalDate endInclusive);

}
