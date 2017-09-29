package net.meerkat.identifier.instrument.future;

import net.meerkat.identifier.instrument.InstrumentId;

/**
 *
 * @author ollie
 */
public class FutureTicker implements InstrumentId {

    private final String underlying;
    private final FutureDelivery delivery;
    private final String suffix;

    public FutureTicker(final String underlying, final FutureDelivery delivery) {
        this(underlying, delivery, null);
    }

    public FutureTicker(final String underlying, final FutureDelivery delivery, final String suffix) {
        this.underlying = underlying;
        this.delivery = delivery;
        this.suffix = suffix;
    }

    public String underlying() {
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
                + (suffix == null ? "" : ' ' + suffix);
    }

}
