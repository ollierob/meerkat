package net.meerkat.security.derivative.option.exercise;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Ollie
 */
public abstract class AbstractOptionExercise implements OptionExercise {

    @XmlAttribute(name = "expiration")
    private LocalDate expiration;

    @Deprecated
    AbstractOptionExercise() {
    }

    protected AbstractOptionExercise(final LocalDate expiration) {
        this.expiration = expiration;
    }

    @Override
    public LocalDate expiration() {
        return expiration;
    }

}
