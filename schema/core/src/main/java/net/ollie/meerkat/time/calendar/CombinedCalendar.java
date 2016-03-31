package net.ollie.meerkat.time.calendar;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class CombinedCalendar implements BusinessCalendar {

    @XmlElementRef(name = "first")
    private BusinessCalendar first;

    @XmlElementRef(name = "second")
    private BusinessCalendar second;

    @Deprecated
    CombinedCalendar() {
    }

    public CombinedCalendar(final BusinessCalendar first, final BusinessCalendar second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isHoliday(final LocalDate date) {
        return first.isHoliday(date) || second.isHoliday(date);
    }

}
