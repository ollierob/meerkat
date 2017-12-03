package net.meerkat.instrument.derivative.option.exercise;

import net.meerkat.temporal.date.years.Years;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author Ollie
 */
public interface OptionExercise {

    @Nonnull
    LocalDate expirationDate();

    @Nonnull
    boolean canExerciseOn(LocalDate date);

    @Nonnull
    default Period timeToExpiration(final LocalDate from) {
        return Period.between(from, this.expirationDate());
    }

    default Years yearsToExpiration(final LocalDate from) {
        return Years.between(from, this.expirationDate());
    }

    /**
     * @return exercise ratio.
     */
    @Nonnull
    Number contractMultiplier();

}
