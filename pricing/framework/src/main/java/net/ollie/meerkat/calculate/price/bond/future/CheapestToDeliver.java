package net.ollie.meerkat.calculate.price.bond.future;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Nonnull;

import net.ollie.meerkat.money.currency.Currency;
import net.ollie.meerkat.money.currency.HasCurrency;
import net.ollie.meerkat.Explainable;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.security.bond.Bond;

/**
 *
 * @author Ollie
 */
public interface CheapestToDeliver<C extends Currency>
        extends HasCurrency, Explainable {

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

    @Override
    default Map<String, Object> explain() {
        return new ExplanationBuilder()
                .put("bond", this.bond())
                .put("price", this.price())
                .put("conversion factor", this.conversionFactor())
                .explain();
    }

}
