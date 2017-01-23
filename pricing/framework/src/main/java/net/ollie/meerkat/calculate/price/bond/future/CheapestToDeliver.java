package net.ollie.meerkat.calculate.price.bond.future;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Nonnull;

import net.meerkat.Explainable;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.money.currency.CurrencyId;
import net.meerkat.money.currency.HasCurrency;
import net.ollie.meerkat.calculate.price.bond.BondPrice;

/**
 *
 * @author Ollie
 */
public interface CheapestToDeliver<C extends CurrencyId>
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
        return this.explanationBuilder()
                .put("bond", this.bond())
                .put("price", this.price())
                .put("conversion factor", this.conversionFactor())
                .explain();
    }

}
