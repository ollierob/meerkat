package net.meerkat.money.price;

import java.util.Map;

import net.meerkat.identifier.currency.CurrencyId;

/**
 * A price containing bid and offer values.
 *
 * @author ollie
 */
public interface TwoWayPrice<P, C extends CurrencyId> extends TwoWay<P>, Price<C> {

    /**
     * @return true if the {@link #bid best bid} exceeds the {@link #offer best offer}.
     */
    boolean isCrossed();

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
