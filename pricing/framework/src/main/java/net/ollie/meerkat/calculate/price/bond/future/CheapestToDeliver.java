package net.ollie.meerkat.calculate.price.bond.future;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

import net.ollie.goat.money.currency.Currency;
import net.ollie.goat.money.currency.HasCurrency;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.security.bond.Bond;

/**
 *
 * @author Ollie
 */
public interface CheapestToDeliver<C extends Currency> extends HasCurrency {

    @Nonnull
    BigDecimal conversionFactor();

    @Nonnull
    Bond bond();

    @Nonnull
    BondPrice<C> price();

    @Override
    default C currency() {
        return this.price().currency();
    }

}
