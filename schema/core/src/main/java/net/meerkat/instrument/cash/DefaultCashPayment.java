package net.meerkat.instrument.cash;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.Explainable;
import net.meerkat.money.Money;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.fx.ExchangeRate;

/**
 *
 * @author ollie
 */
public class DefaultCashPayment<C extends CurrencyId> implements CashPayment<C>, Explainable {

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
    public <T extends CurrencyId> DefaultCashPayment<T> convert(final ExchangeRate<C, T> exchangeRate) {
        return new DefaultCashPayment<>(date, amount.convert(exchangeRate));
    }

    @Override
    public String toString() {
        return date + ":" + amount;
    }

    @Override
    public ExplanationBuilder explain() {
        return this.explanationBuilder()
                .put("date", date)
                .put("amount", amount);
    }

}
