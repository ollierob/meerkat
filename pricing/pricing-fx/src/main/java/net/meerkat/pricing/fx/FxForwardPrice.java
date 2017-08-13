package net.meerkat.pricing.fx;

import net.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface FxForwardPrice<C extends CurrencyId> extends FxPrice<C> {
    
    Number forwardPoints();
    
    interface Shiftable<C extends CurrencyId> extends FxForwardPrice<C>, FxPrice.Shiftable<C> {
        
    }

}
