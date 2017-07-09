package net.meerkat.instrument.cash;

import java.time.LocalDate;

import net.meerkat.Explainable;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public class DefaultCashPayment<C extends CurrencyId> implements CashPayment<C>, Explainable {

    private final LocalDate date;
    private final Money<C> amount;

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
        return this.explanationBuilder()
                .put("date", date)
                .put("amount", amount);
    }

}
