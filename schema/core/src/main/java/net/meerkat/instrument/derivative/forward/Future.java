package net.meerkat.instrument.derivative.forward;

import net.meerkat.instrument.Instrument;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface Future<U extends Instrument> extends Forward<U> {

    @Nonnull
    FutureDelivery delivery();
    
}
