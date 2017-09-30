package net.meerkat.instrument.repo.repurchase;

import java.time.LocalDate;
import java.util.Map;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.money.Money;
import net.meerkat.money.interest.fixed.FixedInterestRate;

/**
 * Repurchase accruing at a fixed interest rate until a given {@link #date}.
 *
 * @author ollie
 */
public class ClassicRepoRepurchase implements RepoRepurchase {

    private final FixedInterestRate rate;
    private final LocalDate date;

    public ClassicRepoRepurchase(final FixedInterestRate rate, final LocalDate date) {
        this.rate = rate;
        this.date = date;
    }

    @Nonnull
    public FixedInterestRate rate() {
        return rate;
    }

    @Nonnull
    public LocalDate date() {
        return date;
    }

    public <C extends CurrencyId> Money<C> accrueFrom(final CashPayment<C> purchase) {
        return this.accrueFrom(purchase.paymentAmount(), purchase.paymentDate());
    }

    public <C extends CurrencyId> Money<C> accrueFrom(final Money<C> amount, final LocalDate date) {
        return rate.accrue(amount, date, this.date);
    }

    @Override
    public <R> R handleWith(final Handler<R> handler) {
        return handler.handle(this);
    }

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder().put("rate", rate).put("date", date);
    }

}
