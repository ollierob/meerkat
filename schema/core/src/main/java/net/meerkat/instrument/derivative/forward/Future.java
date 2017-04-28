package net.meerkat.instrument.derivative.forward;

import javax.annotation.Nonnull;

import net.meerkat.instrument.Instrument;
import net.ollie.goat.temporal.date.interim.Interim;

/**
 *
 * @author ollie
 */
public interface Future<U extends Instrument> extends Forward<U> {

    @Nonnull
    Interim deliveryDates();

}
