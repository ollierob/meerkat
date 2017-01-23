package net.meerkat.instrument.cash;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.Explainable;
import net.meerkat.money.Money;
import net.meerkat.money.currency.Currency;

/**
 *
 * @author ollie
 */
public class DefaultCashPayment<C extends Currency> implements CashPayment<C>, Explainable {

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

    @Override
    public ExplanationBuilder explain() {
        return new ExplanationBuilder()
                .put("date", date)
                .put("amount", amount);
    }

}
