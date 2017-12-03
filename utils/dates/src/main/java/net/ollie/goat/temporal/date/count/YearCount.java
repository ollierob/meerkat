package net.ollie.goat.temporal.date.count;

import net.ollie.goat.temporal.date.years.Years;

import javax.annotation.Nonnull;
import java.time.LocalDate;

/**
 * @author Ollie
 */
public interface YearCount {

    @Nonnull
    Years yearsBetween(@Nonnull LocalDate startInclusive, @Nonnull LocalDate endInclusive);

}
