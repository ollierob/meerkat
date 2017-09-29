package net.meerkat.identifier.instrument.future;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.coljate.set.Set;
import net.meerkat.time.calendar.business.BusinessDay;
import net.meerkat.time.calendar.business.BusinessDayCalendar;

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
