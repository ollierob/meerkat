package net.meerkat.calculate.price;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public class DecimalTwoWayPrice<C extends CurrencyId> implements TwoWayPrice<C> {

    @XmlElementRef(name = "currency")
    private C currency;

    @XmlAttribute(name = "bid")
    private BigDecimal bid;

    @XmlAttribute(name = "offer")
    private BigDecimal offer;

    DecimalTwoWayPrice() {
    }

    public DecimalTwoWayPrice(C currency, BigDecimal bid, BigDecimal offer) {
        this.currency = currency;
        this.bid = bid;
        this.offer = offer;
    }

    @Override
    public Money<C> bid() {
        return Money.of(bid, currency);
    }

    @Override
    public Money<C> offer() {
        return Money.of(offer, currency);
    }

}
