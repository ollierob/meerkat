package net.meerkat.instrument.derivative.forward;

import java.time.LocalDate;
import java.time.YearMonth;

import javax.annotation.Nonnull;

import net.ollie.goat.temporal.date.interim.CompleteInterval;

/**
 *
 * @author ollie
 */
public interface FutureDeliveryDates {

    @Nonnull
    CompleteInterval dates();

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
