package net.meerkat.time.calendar;

import net.coljate.set.MutableSet;
import net.coljate.set.Set;
import net.meerkat.objects.Arguments;
import net.meerkat.temporal.date.HasDate;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.function.Function;

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
            Arguments.requireOrThrow(date, this::contains, InvalidDateException::new);
            return this.next(date);
        }
        final Function<LocalDate, D> next = nth > 0 ? this::next : this::previous;
        D d = next.apply(date);
        for (int i = 1; i < Math.abs(nth); i++) {
            d = next.apply(d.date().plusDays(1));
        }
        return d;
    }

    default Set<D> between(final LocalDate start, final LocalDate end) {
        LocalDate current = start;
        final MutableSet<D> set = MutableSet.createHashSet();
        while (!current.isAfter(end)) {
            final D next = this.next(current);
            set.add(next);
            current = next.date().plusDays(1);
        }
        return set;
    }

    default boolean contains(final LocalDate date) throws DateOutOfRangeException {
        return this.next(date).is(date);
    }

    default void requireInRange(final LocalDate date) throws DateOutOfRangeException {
        if (!this.isInRange(date)) {
            throw new DateOutOfRangeException(date);
        }
    }

}
