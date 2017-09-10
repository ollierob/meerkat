package net.meerkat.time.calendar;

import java.time.LocalDate;
import java.util.function.Function;

import javax.annotation.Nonnull;

import net.ollie.goat.temporal.date.HasDate;

/**
 *
 * @author ollie
 */
public interface Calendar<D extends HasDate> {

    boolean isInRange(@Nonnull LocalDate date);

    D next(LocalDate date) throws DateOutOfRangeException;

    D previous(LocalDate date) throws DateOutOfRangeException;

    default D next(final LocalDate date, final int nth) throws DateOutOfRangeException {
        final Function<LocalDate, D> next = nth > 0 ? this::next : this::previous;
        D d = next.apply(date);
        for (int i = 1; i < Math.abs(nth); i++) {
            d = next.apply(d.date());
        }
        return d;
    }

    default boolean is(final LocalDate date) throws DateOutOfRangeException {
        return this.next(date).date().compareTo(date) == 0;
    }

}
