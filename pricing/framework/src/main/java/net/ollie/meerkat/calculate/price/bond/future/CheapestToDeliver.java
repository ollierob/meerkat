package net.ollie.meerkat.calculate.price.bond.future;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Nonnull;

import net.meerkat.Explainable;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.meerkat.identifier.currency.HasCurrencyId;

/**
 *
 * @author Ollie
 */
public interface CheapestToDeliver<C extends CurrencyId>
        extends HasCurrencyId, Explainable {

    @Nonnull
    BigDecimal conversionFactor();

    @Nonnull
    Bond bond();

    @Nonnull
    BondPrice<C> price();

    @Override
    default C currencyId() {
        return this.price().currencyId();
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
