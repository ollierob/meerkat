package net.meerkat.time.calendar.business;

import java.time.LocalDate;

import net.meerkat.time.calendar.DateOutOfRangeException;

/**
 * Business day iff it is considered such in both calendars.
 *
 * @author ollie
 */
public class BusinessDayPairCalendar implements BusinessDayCalendar {

    private final BusinessDayCalendar first, second;

    public BusinessDayPairCalendar(final BusinessDayCalendar first, final BusinessDayCalendar second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isInRange(final LocalDate date) {
        return first.isInRange(date) && second.isInRange(date);
    }

    @Override
    public BusinessDay next(final LocalDate date) throws DateOutOfRangeException {
        LocalDate current = date;
        BusinessDay next = first.next(date);
        while (!second.isBusinessDay(current)) {
            current = current.plusDays(1);
            next = first.next(current);
        }
        return next;
    }

}
