package net.ollie.goat.temporal.date.months;

import java.time.Month;

/**
 * @author ollie
 */
public interface Months extends Iterable<Month> {

    boolean contains(Month month);

}
