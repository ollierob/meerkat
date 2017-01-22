package net.meerkat.time.calendar;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class CombinedBusinessDayCalendar implements BusinessDayCalendar {

    @XmlElementRef(name = "first")
    private BusinessDayCalendar first;

    @XmlElementRef(name = "second")
    private BusinessDayCalendar second;

    @Deprecated
    CombinedBusinessDayCalendar() {
    }

    public CombinedBusinessDayCalendar(final BusinessDayCalendar first, final BusinessDayCalendar second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isHoliday(final LocalDate date) {
        return first.isHoliday(date) || second.isHoliday(date);
    }

}
