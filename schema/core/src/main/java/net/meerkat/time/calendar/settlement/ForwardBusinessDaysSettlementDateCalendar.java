package net.meerkat.time.calendar.settlement;

import java.time.LocalDate;

import net.meerkat.time.calendar.DateOutOfRangeException;
import net.meerkat.time.calendar.business.BusinessDay;
import net.meerkat.time.calendar.business.BusinessDayCalendar;

/**
 *
 * @author Ollie
 */
public class ForwardBusinessDaysSettlementDateCalendar implements SettlementDateCalendar {

    private final int daysForward;
    private final BusinessDayCalendar businessDayCalendar;

    public ForwardBusinessDaysSettlementDateCalendar(int daysForward, BusinessDayCalendar businessDayCalendar) {
        this.daysForward = daysForward;
        this.businessDayCalendar = businessDayCalendar;
    }

    @Override
    public boolean isInRange(final LocalDate date) {
        return businessDayCalendar.isInRange(date);
    }

    @Override
    public SettlementDate next(final LocalDate date) throws DateOutOfRangeException {
        final BusinessDay next = businessDayCalendar.next(date, daysForward);
        return new SettlementDate(next.date());
    }

}
