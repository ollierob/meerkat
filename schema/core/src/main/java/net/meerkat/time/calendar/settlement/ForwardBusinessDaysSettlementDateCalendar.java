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
    private final SettlementDateCache cache;

    public ForwardBusinessDaysSettlementDateCalendar(
            final int daysForward,
            final BusinessDayCalendar businessDayCalendar,
            final SettlementDateCache cache) {
        this.daysForward = daysForward;
        this.businessDayCalendar = businessDayCalendar;
        this.cache = cache;
    }

    @Override
    public boolean isInRange(final LocalDate date) {
        return businessDayCalendar.isInRange(date.plusDays(daysForward));
    }

    @Override
    public SettlementDate next(final LocalDate date) throws DateOutOfRangeException {
        final BusinessDay next = businessDayCalendar.next(date, daysForward);
        return cache.get(next.date()); //new SettlementDate(next.date());
    }

}
