package net.meerkat.pricing.shifts;

import java.util.Map;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.fx.ExchangeRates;

/**
 *
 * @author Ollie
 */
public interface NoExchangeRateShifts extends ExchangeRateShifts {

    NoExchangeRateShifts INSTANCE = new NoExchangeRateShifts() {

        @Override
        public Map<String, Object> explain() {
            return this.explanationBuilder();
        }

    };

    @Override
    default ExchangeRates shift(final ExchangeRates rates) {
        return rates;
    }

    @Override
    default <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shift(final ExchangeRate<F, T> rate) {
        return rate;
    }

}
