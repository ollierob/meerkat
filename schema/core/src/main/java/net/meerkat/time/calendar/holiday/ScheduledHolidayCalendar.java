package net.meerkat.time.calendar.holiday;

import java.time.LocalDate;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.temporal.date.Dates;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class ScheduledHolidayCalendar implements HolidayCalendar {

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
    public boolean isHoliday(final LocalDate date) {
        if (!this.isSupported(date)) {
            throw new UnsupportedDateException(date);
        }
        return holidays.contains(date);
    }

    @Override
    public boolean isSupported(final LocalDate date) {
        return Dates.areOrdered(validFrom, date, validTo);
    }

}
