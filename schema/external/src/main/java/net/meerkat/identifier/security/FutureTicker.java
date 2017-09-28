package net.meerkat.identifier.security;

import java.time.Month;
import java.time.YearMonth;
import java.util.function.Function;

import net.meerkat.identifier.instrument.InstrumentId;

/**
 *
 * @author ollie
 */
public class FutureTicker implements InstrumentId {

    private static final long serialVersionUID = 1L;

    private final String underlying;
    private final YearMonth delivery;
    private final String suffix;

    public FutureTicker(final String underlying, final YearMonth delivery, final String suffix) {
        this.underlying = underlying;
        this.delivery = delivery;
        this.suffix = suffix;
    }

    public String underlying() {
        return underlying;
    }

    public YearMonth yearMonth() {
        return delivery;
    }

    public String suffix() {
        return suffix;
    }

    public String toString(final Function<YearMonth, String> dateToString) {
        return underlying
                + dateToString.apply(delivery)
                + (suffix == null ? "" : ' ' + suffix);
    }

    @Override
    public String toString() {
        return this.toString(FutureDeliveryMonth::toString);
    }


}
