package net.meerkat.time.calendar.settlement;

import net.meerkat.time.calendar.Calendar;
import net.meerkat.time.calendar.business.BusinessDayCalendar;

/**
 *
 * @author ollie
 */
public interface SettlementDateCalendar extends Calendar<SettlementDate> {

    static SettlementDateCalendar nthBusinessDay(final int n, final BusinessDayCalendar businessDayCalendar) {
        return new ForwardBusinessDaysSettlementDateCalendar(n, businessDayCalendar);
    }

}
