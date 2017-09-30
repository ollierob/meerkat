package net.meerkat.instrument.derivative.option.exercise;

import java.time.LocalDate;

/**
 *
 * @author Ollie
 */
public class AmericanOptionExercise extends AbstractOptionExercise {

    public AmericanOptionExercise(final LocalDate expiration, final Number contractMultiplier) {
        super(expiration, contractMultiplier);
    }

    @Override
    public boolean canExerciseOn(final LocalDate date) {
        return !date.isAfter(this.expirationDate());
    }

}
