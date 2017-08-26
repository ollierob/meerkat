package net.meerkat.money.price;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 * A price containing bid and offer values.
 *
 * @author ollie
 */
public interface TwoWayPrice<C extends CurrencyId> extends Price<C> {

    @Nonnull
    Money<C> bid();

    @Nonnull
    Money<C> offer();

    default Money<C> spread() {
        return this.offer().minus(this.bid());
    }

    default Money<C> mid() {
        return this.bid().plus(this.offer()).over(2);
    }

    @Override
    default C currencyId() {
        return this.bid().currencyId();
    }

    default boolean hasSpread() {
        return !this.mid().isZero();
    }

    @Override
    default Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("bid", this.bid())
                .put("offer", this.offer());
    }

    static <C extends CurrencyId> TwoWayPrice<C> of(final Money<C> bid, final Money<C> offer) {
        throw new UnsupportedOperationException();
    }

    static <C extends CurrencyId> TwoWayPrice<C> of(final C currency, final BigDecimal bid, final BigDecimal offer) {
        return new BigDecimalTwoWayPrice<>(currency, bid, offer);
    }

}
