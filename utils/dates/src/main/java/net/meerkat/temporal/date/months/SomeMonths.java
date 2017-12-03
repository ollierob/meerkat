package net.meerkat.temporal.date.months;

import net.coljate.set.Set;

import java.time.Month;
import java.util.Iterator;

/**
 * @author ollie
 */
public class SomeMonths implements Months {

    static final SomeMonths NONE = new SomeMonths(Set.of());

    public static Months of(final Month... months) {
        return months.length == 0
                ? NONE
                : new SomeMonths(Set.of(months));
    }

    private final Set<Month> months;

    SomeMonths(final Set<Month> months) {
        this.months = months;
    }

    @Override
    public boolean contains(final Month month) {
        return months.contains(month);
    }

    @Override
    public Iterator<Month> iterator() {
        return months.iterator();
    }

}
