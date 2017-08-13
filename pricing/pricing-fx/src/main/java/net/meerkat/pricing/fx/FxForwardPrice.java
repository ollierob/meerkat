package net.meerkat.pricing.fx;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface FxForwardPrice<C extends CurrencyId> extends FxPrice<C> {

    @Nonnull
    Number bidForwardPoints();

    @Nonnull
    Number offerForwardPoints();

    interface Shiftable<C extends CurrencyId> extends FxForwardPrice<C>, FxPrice.Shiftable<C> {

    }

}
