package net.meerkat.instrument.derivative.forward;

import net.coljate.set.Set;
import net.meerkat.time.calendar.business.BusinessDay;
import net.meerkat.time.calendar.business.BusinessDayCalendar;

import javax.annotation.Nonnull;
import java.time.LocalDate;

/**
 *
 * @author ollie
 */
public interface FutureDelivery {

    @Nonnull
    Set<BusinessDay> dates(@Nonnull LocalDate referenceDate, @Nonnull BusinessDayCalendar calendar);

    @Nonnull
    String toDeliveryString();

}
