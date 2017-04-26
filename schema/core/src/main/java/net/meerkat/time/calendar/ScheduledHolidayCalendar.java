package net.meerkat.time.calendar;

import java.time.LocalDate;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.utils.Require;
import net.ollie.goat.temporal.date.Dates;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class ScheduledHolidayCalendar implements BusinessDayCalendar {

    @XmlAttribute(name = "validFrom")
    private LocalDate validFrom;

    @XmlAttribute(name = "validTo")
    private LocalDate validTo;

    @XmlElement(name = "holiday")
    private Set<LocalDate> holidays;

    @Deprecated
    ScheduledHolidayCalendar() {
    }

    public ScheduledHolidayCalendar(final LocalDate validFrom, final LocalDate validTo, final Set<LocalDate> holidays) {
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.holidays = holidays;
    }

    @Override
    public boolean isBusinessDay(final LocalDate date) {
        return !this.isHoliday(date);
    }

    @Override
    public boolean isHoliday(final LocalDate date) {
        if (holidays.contains(validTo)) {
            return true;
        }
        Require.that(Dates.areOrdered(validFrom, date, validTo), () -> "Date [" + date + "] is outside range");
        return false;
    }

}
