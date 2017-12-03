package net.ollie.goat.temporal.date;

import net.ollie.goat.functions.Functions;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ollie
 */
public abstract class Dates {

    protected Dates() {
        throw new AbstractMethodError();
    }

    public static boolean equals(final LocalDate left, final LocalDate right) {
        return Functions.ifBothNonNull(left, right, (final LocalDate l, final LocalDate r) -> l == r || l.compareTo(r) == 0);
    }

    public static boolean areOrdered(final LocalDate d1, final LocalDate d2) {
        return !d1.isAfter(d2);
    }

    public static boolean areOrdered(final LocalDate d1, final LocalDate d2, final LocalDate d3) {
        return areOrdered(d1, d2) && areOrdered(d2, d3);
    }

    public static LocalDate min(final LocalDate d1, final LocalDate d2) {
        return d2.isAfter(d1) ? d1 : d2;
    }

    public static LocalDate max(final LocalDate d1, final LocalDate d2) {
        return d2.isAfter(d1) ? d2 : d1;
    }

    public static Iterable<LocalDate> over(final LocalDate start, final LocalDate endExclusive) {
        return () -> new Iterator<LocalDate>() {

            private LocalDate current = start;

            @Override
            public boolean hasNext() {
                return current.isBefore(endExclusive);
            }

            @Override
            public LocalDate next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                LocalDate next = current;
                current = next.plusDays(1);
                return next;
            }

        };
    }

}
