package net.meerkat.security.derivative.option.exercise;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class AmericanOptionExercise extends AbstractOptionExercise {

    @Deprecated
    AmericanOptionExercise() {
    }

    public AmericanOptionExercise(final LocalDate expiration) {
        super(expiration);
    }

    @Override
    public boolean canExerciseOn(final LocalDate date) {
        return !date.isAfter(this.expiration());
    }

}
