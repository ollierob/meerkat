package net.meerkat.money.price;

import java.math.BigDecimal;
import java.math.RoundingMode;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.ollie.goat.numeric.BigDecimals;

/**
 *
 * @author ollie
 */
public class BigDecimalTwoWayPrice<C extends CurrencyId> implements TwoWayPrice<C> {

    private final C currency;
    private final BigDecimal bid;
    private final BigDecimal offer;

    private transient Money<C> bidMoney;
    private transient Money<C> offerMoney;

    public BigDecimalTwoWayPrice(final C currency, final BigDecimal bid, final BigDecimal offer) {
        this.currency = currency;
        this.bid = bid;
        this.offer = offer;
    }

    @Override
    public Money<C> bid() {
        return bidMoney == null
                ? bidMoney = Money.of(currency, bid)
                : bidMoney;
    }

    @Override
    public Money<C> offer() {
        return offerMoney == null
                ? offerMoney = Money.of(currency, offer)
                : offerMoney;
    }

    @Override
    public Money<C> mid() {
        return Money.of(currency, bid.add(offer).divide(BigDecimals.TWO, RoundingMode.HALF_UP));
    }

    @Override
    @Deprecated
    public BigDecimalTwoWayPrice<C> evaluate() {
        return this;
    }

}
