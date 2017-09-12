package net.meerkat.pricing.moneymarket;

import java.util.Map;

import net.meerkat.pricing.shifts.ExchangeRateShifts;
import net.meerkat.pricing.shifts.SecurityShifts;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.fx.ExchangeRate;

/**
 *
 * @author ollie
 */
public interface RepoShifts extends ExchangeRateShifts {

    static RepoShifts none() {
        return NoRepoShifts.INSTANCE;
    }

    static RepoShifts cast(final SecurityShifts shifts) {
        throw new UnsupportedOperationException();
    }

    class NoRepoShifts implements RepoShifts {

        static final NoRepoShifts INSTANCE = new NoRepoShifts();

        @Override
        public <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shift(ExchangeRate<F, T> rate) {
            return rate;
        }

        @Override
        public Map<String, Object> explain() {
            return this.explanationBuilder();
        }

    }

}
