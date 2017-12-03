package net.meerkat.temporal.date.months;

import net.meerkat.collections.Iterators;

import java.time.Month;
import java.util.Iterator;

/**
 * @author ollie
 */
class AllMonths implements Months {

    static final AllMonths INSTANCE = new AllMonths();

    @Override
    public boolean contains(final Month month) {
        return true;
    }

    @Override
    public Iterator<Month> iterator() {
        return Iterators.of(Month.values());
    }

}
