package net.meerkat.pricing.fx;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.ollie.goat.numeric.fraction.BigDecimalFraction;

/**
 *
 * @author ollie
 */
public interface FxForwardPrice<C extends CurrencyId> extends FxPrice<C> {
    
    @Nonnull
    Number forwardPoints();
    
    @Nonnull
    default Number forwardPointsPips() {
        return BigDecimalFraction.of(this.forwardPoints()).times(1000);
    }
    
    interface Shiftable<C extends CurrencyId> extends FxForwardPrice<C>, FxPrice.Shiftable<C> {
        
    }
    
}
