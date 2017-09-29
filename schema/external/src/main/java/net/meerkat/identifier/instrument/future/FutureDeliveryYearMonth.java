package net.meerkat.identifier.instrument.future;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

import net.coljate.set.Set;
import net.meerkat.time.calendar.business.BusinessDay;
import net.meerkat.time.calendar.business.BusinessDayCalendar;

/**
 *
 * @author ollie
 */
public class FutureDeliveryYearMonth implements FutureDelivery {

    private final YearMonth yearMonth;

    public FutureDeliveryYearMonth(final YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    public int year() {
        return yearMonth.getYear();
    }

    public Month month() {
        return yearMonth.getMonth();
    }

    public char deliveryYear() {
        return Character.forDigit(this.year() % 10, 10);
    }

    public FutureDeliveryMonth deliveryMonth() {
        return FutureDeliveryMonth.valueOf(yearMonth.getMonth());
    }

    @Override
    public Set<BusinessDay> dates(final LocalDate referenceDate, final BusinessDayCalendar calendar) {
        return calendar.between(yearMonth.atDay(1), yearMonth.atEndOfMonth());
    }

    @Override
    public String toDeliveryString() {
        return "" + this.deliveryMonth().character() + this.deliveryYear();
    }

}
