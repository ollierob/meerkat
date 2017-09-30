package net.meerkat.instrument.derivative.option.exercise;

import java.time.LocalDate;
import java.time.Period;

import javax.annotation.Nonnull;

import net.ollie.goat.temporal.date.years.Years;

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
