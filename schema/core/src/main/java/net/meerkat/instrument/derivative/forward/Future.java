package net.meerkat.instrument.derivative.forward;

import javax.annotation.Nonnull;

import net.meerkat.instrument.Instrument;

/**
 *
 * @author ollie
 */
public interface Future<U extends Instrument> extends Forward<U> {

    @Nonnull
    FutureDeliveryDates dates();

}
