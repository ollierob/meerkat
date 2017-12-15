package net.meerkat.identifier.instrument.future;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.instrument.derivative.forward.FutureDelivery;

/**
 *
 * @author ollie
 */
public class FutureTicker implements InstrumentId {

    private final FutureUnderlyingId underlying;
    private final FutureDelivery delivery;

    public FutureTicker(final FutureUnderlyingId underlying, final FutureDelivery delivery) {
        this.underlying = underlying;
        this.delivery = delivery;
    }

    public FutureUnderlyingId underlying() {
        return underlying;
    }

    public FutureDelivery delivery() {
        return delivery;
    }

    public String toTickerString() {
        return underlying.name()
                + delivery.toDeliveryString();
    }

}
