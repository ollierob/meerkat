package net.meerkat.instrument.derivative.option.exercise;

import java.time.LocalDate;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Ollie
 */
public class BermudanOptionExercise implements OptionExercise {

    private final SortedSet<LocalDate> exerciseDates;
    private final Number contractMultiplier;

    public BermudanOptionExercise(final Collection<LocalDate> exerciseDates, final Number contractMultiplier) {
        this.exerciseDates = new TreeSet<>(exerciseDates);
        this.contractMultiplier = contractMultiplier;
    }

    @Override
    public boolean canExerciseOn(final LocalDate date) {
        return exerciseDates.contains(date);
    }

    @Override
    public LocalDate expirationDate() {
        return exerciseDates.last();
    }

    @Override
    public Number contractMultiplier() {
        return contractMultiplier;
    }

}
