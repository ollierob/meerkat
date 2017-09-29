package net.meerkat.identifier.instrument.future;

import net.meerkat.Named;

/**
 *
 * @author ollie
 * @see <a href="http://www.futuresknowledge.com/trading-tools/ticker-symbols/">Ticker symbols</a>
 */
public class FutureUnderlyingId extends Named {

    public static final FutureUnderlyingId CRUDE_OIL = of("CL");

    public static final FutureUnderlyingId COPPER = of("HG");
    public static final FutureUnderlyingId GOLD = of("GC");
    public static final FutureUnderlyingId PLATINUM = of("PL");
    public static final FutureUnderlyingId PALLADIUM = of("PA");
    public static final FutureUnderlyingId SILVER_5K_OZ = of("SI");
    public static final FutureUnderlyingId SILVER_10K_OZ = of("AG");

    public static final FutureUnderlyingId CORN = of("C");
    public static final FutureUnderlyingId OATS = of("O");

    public static final FutureUnderlyingId LUMBER = of("LB");

    public static FutureUnderlyingId of(final String value) {
        return new FutureUnderlyingId(value);
    }

    protected FutureUnderlyingId(final String value) {
        super(value);
    }

}
