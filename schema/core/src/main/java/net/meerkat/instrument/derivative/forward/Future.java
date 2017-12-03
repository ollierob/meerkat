package net.meerkat.instrument.derivative.forward;

import net.meerkat.instrument.Instrument;
import net.meerkat.temporal.date.interim.Interim;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface Future<U extends Instrument> extends Forward<U> {

    @Nonnull
    Interim deliveryDates();
    
}
