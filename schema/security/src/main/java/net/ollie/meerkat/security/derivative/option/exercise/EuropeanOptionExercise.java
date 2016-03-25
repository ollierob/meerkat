package net.ollie.meerkat.security.derivative.option.exercise;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.date.Dates;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class EuropeanOptionExercise extends AbstractOptionExercise {

    @Deprecated
    EuropeanOptionExercise() {
    }

    public EuropeanOptionExercise(final LocalDate expiration) {
        super(expiration);
    }

    @Override
    public boolean canExerciseOn(final LocalDate date) {
        return Dates.equals(date, this.expiration());
    }

}
