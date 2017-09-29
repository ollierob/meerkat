package net.meerkat.identifier.instrument.future;

import java.util.function.Function;

import net.meerkat.identifier.instrument.InstrumentId;

/**
 *
 * @author ollie
 */
public class FutureTicker implements InstrumentId {

    private final String underlying;
    private final FutureDelivery delivery;
    private final String suffix;

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

    public String toString(final Function<? super FutureDelivery, String> deliveryToString) {
        return underlying
                + deliveryToString.apply(delivery)
                + (suffix == null ? "" : ' ' + suffix);
    }

    @Override
    public String toString() {
        return this.toString(Object::toString);
    }

}
