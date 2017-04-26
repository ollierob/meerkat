package net.meerkat.time.calendar;

import java.time.LocalDate;
import java.util.Collection;

/**
 *
 * @author ollie
 */
public class AggregateBusinessDayCalendar implements BusinessDayCalendar {

    private BusinessDayCalendar[] calendars;

    public AggregateBusinessDayCalendar(final Collection<BusinessDayCalendar> calendars) {
        this.calendars = calendars.toArray(new BusinessDayCalendar[calendars.size()]);
    }

    @Override
    public boolean isBusinessDay(final LocalDate date) {
        for (final BusinessDayCalendar calendar : calendars) {
            if (calendar.isBusinessDay(date)) {
                return true;
            }
        }
        return false;
    }

}
