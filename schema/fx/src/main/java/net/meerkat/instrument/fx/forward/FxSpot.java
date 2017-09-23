package net.meerkat.instrument.fx.forward;

import net.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface FxSpot<B extends CurrencyId, C extends CurrencyId>
        extends OutrightFxForward<B, C> {

}
