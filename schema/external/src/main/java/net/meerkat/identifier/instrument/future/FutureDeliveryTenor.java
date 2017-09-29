package net.meerkat.identifier.instrument.future;

import java.time.LocalDate;

import net.coljate.set.Set;
import net.meerkat.money.interest.curve.Tenor;
import net.meerkat.time.calendar.business.BusinessDay;
import net.meerkat.time.calendar.business.BusinessDayCalendar;

/**
 *
 * @author ollie
 */
public class FutureDeliveryTenor implements FutureDelivery {

    public static FutureDeliveryTenor of(final Tenor tenor) {
        return new FutureDeliveryTenor(tenor);
    }

    private final Tenor tenor;

    protected FutureDeliveryTenor(final Tenor tenor) {
        this.tenor = tenor;
    }

    @Override
    public Set<BusinessDay> dates(final LocalDate referenceDate, final BusinessDayCalendar calendar) {
        final LocalDate forward = referenceDate.plus(tenor.period());
        final BusinessDay next = calendar.next(forward);
        return Set.of(next);
    }

    @Override
    public String toDeliveryString() {
        return tenor.toTenorString();
    }

}
