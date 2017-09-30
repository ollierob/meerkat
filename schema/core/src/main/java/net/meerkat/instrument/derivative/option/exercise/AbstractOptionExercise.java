package net.meerkat.instrument.derivative.option.exercise;

import java.time.LocalDate;

/**
 *
 * @author Ollie
 */
public abstract class AbstractOptionExercise implements OptionExercise {

    private final LocalDate expiration;
    private final Number contractMultiplier;

    protected AbstractOptionExercise(final LocalDate expiration, final Number contractMultiplier) {
        this.expiration = expiration;
        this.contractMultiplier = contractMultiplier;
    }

    @Override
    public LocalDate expirationDate() {
        return expiration;
    }

    @Override
    public Number contractMultiplier() {
        return contractMultiplier;
    }

}
