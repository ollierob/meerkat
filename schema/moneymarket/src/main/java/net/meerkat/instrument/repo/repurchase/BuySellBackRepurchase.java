package net.meerkat.instrument.repo.repurchase;

import java.time.LocalDate;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.cash.DefaultCashPayment;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public class BuySellBackRepurchase<C extends CurrencyId>
        extends DefaultCashPayment<C>
        implements RepoRepurchase {

    public BuySellBackRepurchase(final LocalDate date, final Money<C> amount) {
        super(date, amount);
    }

    @Override
    public <R> R handleWith(final Handler<R> handler) {
        return handler.handle(this);
    }

}
