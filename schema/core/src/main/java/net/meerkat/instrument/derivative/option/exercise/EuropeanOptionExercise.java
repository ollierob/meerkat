package net.meerkat.instrument.derivative.option.exercise;

import net.meerkat.temporal.date.Dates;

import java.time.LocalDate;

/**
 *
 * @author Ollie
 */
public class EuropeanOptionExercise extends AbstractOptionExercise {

    public EuropeanOptionExercise(final LocalDate expiration, final Number contractMultiplier) {
        super(expiration, contractMultiplier);
    }

    @Override
    public boolean canExerciseOn(final LocalDate date) {
        return Dates.equals(date, this.expirationDate());
    }

}
