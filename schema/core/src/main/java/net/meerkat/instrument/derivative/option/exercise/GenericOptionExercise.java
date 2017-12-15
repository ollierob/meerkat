package net.meerkat.instrument.derivative.option.exercise;

import net.coljate.set.Set;

import javax.annotation.Nonnull;
import java.time.LocalDate;

public class GenericOptionExercise extends AbstractOptionExercise {

    private final Set<LocalDate> exerciseDates;

    protected GenericOptionExercise(
            final LocalDate expiration,
            final Number contractMultiplier,
            final Set<LocalDate> exerciseDates) {
        super(expiration, contractMultiplier);
        this.exerciseDates = exerciseDates;
    }

    @Nonnull
    @Override
    public boolean canExerciseOn(final LocalDate date) {
        return exerciseDates.contains(date);
    }

}
