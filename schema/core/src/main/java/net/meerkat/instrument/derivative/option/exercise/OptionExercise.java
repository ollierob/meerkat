package net.meerkat.instrument.derivative.option.exercise;

import java.time.LocalDate;
import java.time.Period;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface OptionExercise {

    @Nonnull
    LocalDate expiration();

    @Nonnull
    boolean canExerciseOn(LocalDate date);

    @Nonnull
    default Period timeToExpiration(final LocalDate from) {
        return Period.between(from, this.expiration());
    }

}
