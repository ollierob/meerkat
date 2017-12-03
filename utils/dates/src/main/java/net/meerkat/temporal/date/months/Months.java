package net.meerkat.temporal.date.months;

import java.time.Month;

/**
 * @author ollie
 */
public interface Months extends Iterable<Month> {

    boolean contains(Month month);

    static Months all() {
        return AllMonths.INSTANCE;
    }

    static Months none() {
        return SomeMonths.NONE;
    }

    static Months of(final Month... months) {
        return SomeMonths.of(months);
    }

}
