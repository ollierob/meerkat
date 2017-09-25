package net.meerkat.pricing.moneymarket.shifts;

import java.util.Map;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.interest.InterestRate;

/**
 *
 * @author Ollie
 */
public class NoRepoShifts implements RepoShifts {

    static final NoRepoShifts INSTANCE = new NoRepoShifts();

    @Override
    public InterestRate shiftRepoRate(final InterestRate rate) {
        return rate;
    }

    @Override
    public <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shift(ExchangeRate<F, T> rate) {
        return rate;
    }

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder();
    }

}
