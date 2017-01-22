package net.meerkat.security.fx;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.money.currency.Currency;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public class DefaultCashPayment<C extends Currency> implements CashPayment<C> {

    @XmlAttribute(name = "date")
    private LocalDate date;

    @XmlElementRef(name = "amount")
    private Money<C> amount;

    @Deprecated
    DefaultCashPayment() {
    }

    public DefaultCashPayment(final LocalDate date, final Money<C> amount) {
        this.date = date;
        this.amount = amount;
    }

    @Override
    public LocalDate date() {
        return date;
    }

    @Override
    public Money<C> amount() {
        return amount;
    }

    @Override
    public String toString() {
        return date + ":" + amount;
    }

}
