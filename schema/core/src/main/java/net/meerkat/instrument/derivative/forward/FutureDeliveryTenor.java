package net.meerkat.instrument.derivative.forward;

import net.coljate.set.Set;
import net.meerkat.money.interest.curve.Tenor;
import net.meerkat.time.calendar.business.BusinessDay;
import net.meerkat.time.calendar.business.BusinessDayCalendar;

import java.time.LocalDate;

/**
 * @author ollie
 */
public record FutureDeliveryTenor(Tenor tenor) implements FutureDelivery {

    @Override
    public Set<BusinessDay> dates(final LocalDate referenceDate, final BusinessDayCalendar calendar) {
        final var forward = referenceDate.plus(tenor.period());
        final var next = calendar.next(forward);
        return Set.of(next);
    }

    @Override
    public String toDeliveryString() {
        return tenor.toTenorString();
    }

}
