package net.meerkat.pricing;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.Price;

/**
 *
 * @author ollie
 */
public interface TwoWayPrice<C extends CurrencyId> extends Price<C> {

    @Nonnull
    Money<C> bid();

    @Nonnull
    Money<C> offer();

    default Money<C> spread() {
        return offer().minus(bid());
    }

    default Money<C> mid() {
        return bid().plus(offer()).over(2);
    }

    @Override
    default CurrencyId currencyId() {
        return this.bid().currencyId();
    }

    @Override
    default Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("bid", this.bid())
                .put("offer", this.offer());
    }

    static <C extends CurrencyId> TwoWayPrice<C> of(final C currency, final BigDecimal bid, final BigDecimal offer) {
        return new DecimalTwoWayPrice<>(currency, bid, offer);
    }

}
