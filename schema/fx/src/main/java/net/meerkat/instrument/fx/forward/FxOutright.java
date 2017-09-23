package net.meerkat.instrument.fx.forward;

import net.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface FxOutright<B extends CurrencyId, C extends CurrencyId>
        extends DeliverableFxForward<B, C> {

}
