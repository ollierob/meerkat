package net.meerkat.time.calendar.settlement;

import java.time.LocalDate;
import java.util.Objects;

import net.meerkat.time.calendar.DateOutOfRangeException;

/**
 * A date is considered a settlement date iff two calendars both agree on this.
 *
 * @author ollie
 */
public class SettlementDatePairCalendar implements SettlementDateCalendar {

    private final SettlementDateCalendar first, second;

    public SettlementDatePairCalendar(final SettlementDateCalendar first, final SettlementDateCalendar second) {
        this.first = Objects.requireNonNull(first);
        this.second = Objects.requireNonNull(second);
    }

    @Override
    public boolean isInRange(final LocalDate date) {
        return first.isInRange(date) && second.isInRange(date);
    }

    @Override
    public SettlementDate next(final LocalDate date) throws DateOutOfRangeException {
        LocalDate current = date;
        SettlementDate next;
        do {
            next = first.next(current);
            current = current.plusDays(1);
        } while (!second.isSettlementDate(next.date()));
        return next;
    }

}
