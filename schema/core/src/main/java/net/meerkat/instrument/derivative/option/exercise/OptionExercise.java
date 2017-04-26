package net.meerkat.instrument.derivative.option.exercise;

import java.time.LocalDate;

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

}
