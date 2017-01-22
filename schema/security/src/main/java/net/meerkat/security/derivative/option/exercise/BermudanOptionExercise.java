package net.meerkat.security.derivative.option.exercise;

import java.time.LocalDate;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Ollie
 */
public class BermudanOptionExercise implements OptionExercise {

    @XmlElement(name = "exercise")
    private SortedSet<LocalDate> exerciseDates;

    @Deprecated
    BermudanOptionExercise() {
    }

    public BermudanOptionExercise(final Collection<LocalDate> exerciseDates) {
        this.exerciseDates = new TreeSet<>(exerciseDates);
    }

    @Override
    public boolean canExerciseOn(final LocalDate date) {
        return exerciseDates.contains(date);
    }

    @Override
    public LocalDate expiration() {
        return exerciseDates.last();
    }

}
