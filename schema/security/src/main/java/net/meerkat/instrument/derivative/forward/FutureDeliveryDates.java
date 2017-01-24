package net.meerkat.instrument.derivative.forward;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.SortedSet;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface FutureDeliveryDates {

    @Nonnull
    SortedSet<LocalDate> dates();

    default LocalDate earliest() {
        return this.dates().first();
    }

    default LocalDate latest() {
        return this.dates().last();
    }

    default LocalDate expiry() {
        return this.latest();
    }

    static FutureDeliveryDates any(final YearMonth month) {
        return new YearMonthFutureDeliveryDates(month);
    }

}
