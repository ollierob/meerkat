package net.meerkat.pricing;

import java.math.BigDecimal;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public class DecimalTwoWayPrice<C extends CurrencyId> implements TwoWayPrice<C> {

    private final C currency;
    private final BigDecimal bid;
    private final BigDecimal offer;
    private transient Money<C> bidMoney;
    private transient Money<C> offerMoney;

    public DecimalTwoWayPrice(final C currency, final BigDecimal bid, final BigDecimal offer) {
        this.currency = currency;
        this.bid = bid;
        this.offer = offer;
    }

    @Override
    public Money<C> bid() {
        return bidMoney == null
                ? bidMoney = Money.of(bid, currency)
                : bidMoney;
    }

    @Override
    public Money<C> offer() {
        return offerMoney == null
                ? offerMoney = Money.of(offer, currency)
                : offerMoney;
    }

}
