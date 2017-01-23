package net.meerkat.instrument.derivative.forward;

import javax.annotation.Nonnull;

import net.meerkat.instrument.Instrument;

/**
 *
 * @author ollie
 */
public interface Future<S extends Instrument> extends Forward<S> {

    @Nonnull
    FutureDeliveryDates dates();

}
