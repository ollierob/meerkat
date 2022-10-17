package net.meerkat.identifier.instrument.future;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.instrument.derivative.forward.FutureDelivery;

/**
 * @author ollie
 */
public record FutureTicker(FutureUnderlyingId underlying, FutureDelivery delivery) implements InstrumentId {

    public String toTickerString() {
        return underlying.name()
                + delivery.toDeliveryString();
    }

}
