package net.meerkat.time.calendar.holiday;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class WeekendHolidayCalendar implements HolidayCalendar {

    @Override
    public boolean isSupported(final LocalDate date) {
        return true;
    }

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
