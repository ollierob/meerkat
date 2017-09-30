package net.meerkat.instrument.repo.repurchase;

import java.time.LocalDate;
import java.util.Map;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.meerkat.money.interest.fixed.SimpleFixedInterestRate;

/**
 * Repurchase accruing at a fixed interest rate, but with no maturity date.
 *
 * @author ollie
 */
public class OpenRepoRepurchase implements RepoRepurchase {

    private final SimpleFixedInterestRate rate;

    public OpenRepoRepurchase(final SimpleFixedInterestRate rate) {
        this.rate = rate;
    }

    @Nonnull
    public SimpleFixedInterestRate rate() {
        return rate;
    }

    @CheckReturnValue
    public ClassicRepoRepurchase close(final LocalDate repurchaseDate) {
        return new ClassicRepoRepurchase(this.rate(), repurchaseDate);
    }

    @Override
    public <R> R handleWith(final Handler<R> handler) {
        return handler.handle(this);
    }

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder().put("rate", rate);
    }

}
