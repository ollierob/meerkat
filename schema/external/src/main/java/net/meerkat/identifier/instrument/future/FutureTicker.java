package net.meerkat.identifier.instrument.future;

import net.meerkat.identifier.instrument.InstrumentId;

/**
 *
 * @author ollie
 */
public class FutureTicker implements InstrumentId {

    private final FutureUnderlyingId underlying;
    private final FutureDelivery delivery;
    private final String suffix;

    public FutureTicker(final FutureUnderlyingId underlying, final FutureDelivery delivery) {
        this(underlying, delivery, null);
    }

    public FutureTicker(final FutureUnderlyingId underlying, final FutureDelivery delivery, final String suffix) {
        this.underlying = underlying;
        this.delivery = delivery;
        this.suffix = suffix;
    }

    public FutureUnderlyingId underlying() {
        return underlying;
    }

    public FutureDelivery delivery() {
        return delivery;
    }

    public String suffix() {
        return suffix;
    }

    public String toTickerString() {
        return underlying
                + delivery.toDeliveryString()
                + (suffix == null ? "" : suffix);
    }

}
