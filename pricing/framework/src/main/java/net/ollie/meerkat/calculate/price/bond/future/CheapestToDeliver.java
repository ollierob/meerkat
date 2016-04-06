package net.ollie.meerkat.calculate.price.bond.future;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.Bond;

/**
 *
 * @author Ollie
 */
public interface CheapestToDeliver<C extends CurrencyId> {

    @Nonnull
    Bond bond();

    @Nonnull
    BondPrice<C> price();

}
