package net.meerkat.money.price;

import net.meerkat.identifier.currency.CurrencyId;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * A price containing bid and offer values.
 *
 * @author ollie
 * @see TwoWayMoney
 */
public interface TwoWayPrice<P, C extends CurrencyId> extends TwoWay<P>, Price<C> {

    @Nonnull
    P mid();

    /**
     * @return true if the {@link #bid} exceeds the {@link #offer}.
     */
    boolean isCrossed();

    /**
     * @return true if there is any difference between the {@link #bid} and {@link #offer}.
     */
    boolean hasSpread();

    @Override
    default Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("bid", this.bid())
                .put("offer", this.offer());
    }

    @Override
    TwoWayPrice<P, C> evaluate();

}
