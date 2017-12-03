package net.meerkat.temporal.date.months;

import net.meerkat.collections.Iterators;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.Month;
import java.util.Iterator;

/**
 * @author ollie
 */
@XmlRootElement
public class AllMonths implements Months {

    @Override
    public boolean contains(final Month month) {
        return true;
    }

    @Override
    public Iterator<Month> iterator() {
        return Iterators.of(Month.values());
    }

}
