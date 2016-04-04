package net.ollie.meerkat.time.calendar;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class WeekendHolidayCalendar implements BusinessDayCalendar {

    @Override
    public boolean isHoliday(final LocalDate date) {
        switch (date.getDayOfWeek()) {
            case SATURDAY:
            case SUNDAY:
                return true;
            default:
                return false;
        }
    }

}
