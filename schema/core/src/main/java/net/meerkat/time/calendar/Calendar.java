package net.meerkat.time.calendar;

import java.time.LocalDate;
import java.util.function.Function;

import javax.annotation.Nonnull;

import net.meerkat.utils.Require;
import net.ollie.goat.temporal.date.HasDate;

/**
 *
 * @author ollie
 */
public interface Calendar<D extends HasDate> {

    boolean isInRange(@Nonnull LocalDate date);

    D next(LocalDate date) throws DateOutOfRangeException;

    default D previous(final LocalDate date) throws DateOutOfRangeException {
        final D next = this.next(date);
        D previous;
        LocalDate current = date;
        do {
            current = current.minusDays(1);
            previous = this.next(current);
        } while (previous.equals(next));
        return previous;
    }

    default D next(final LocalDate date, final int nth) throws InvalidDateException {
        if (nth == 0) {
            Require.that(date, this::is, InvalidDateException::new);
            return this.next(date);
        }
        final Function<LocalDate, D> next = nth > 0 ? this::next : this::previous;
        D d = next.apply(date);
        for (int i = 1; i < Math.abs(nth); i++) {
            d = next.apply(d.date().plusDays(1));
        }
        return d;
    }

    default boolean is(final LocalDate date) throws DateOutOfRangeException {
        return this.next(date).date().compareTo(date) == 0;
    }

}
