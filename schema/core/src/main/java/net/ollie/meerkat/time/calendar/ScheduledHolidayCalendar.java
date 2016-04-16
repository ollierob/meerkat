package net.ollie.meerkat.time.calendar;

import java.time.LocalDate;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.date.Dates;
import net.ollie.meerkat.utils.Require;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class ScheduledHolidayCalendar implements BusinessDayCalendar {

    @XmlAttribute(name = "from")
    private LocalDate from;

    @XmlAttribute(name = "to")
    private LocalDate to;

    @XmlElement(name = "holiday")
    private Set<LocalDate> holidays;

    @Deprecated
    ScheduledHolidayCalendar() {
    }

    public ScheduledHolidayCalendar(final LocalDate from, final LocalDate to, final Set<LocalDate> holidays) {
        this.from = from;
        this.to = to;
        this.holidays = holidays;
    }

    @Override
    public boolean isHoliday(final LocalDate date) {
        if (holidays.contains(to)) {
            return true;
        }
        Require.that(Dates.areOrdered(from, date, to), () -> "Date [" + date + "] is outside range");
        return false;
    }

}
