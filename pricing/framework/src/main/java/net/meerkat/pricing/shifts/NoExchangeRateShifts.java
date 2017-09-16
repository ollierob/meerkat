package net.meerkat.pricing.shifts;

import java.util.Map;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.fx.ExchangeRates;

/**
 *
 * @author Ollie
 */
class NoExchangeRateShifts implements ExchangeRateShifts {

    static final NoExchangeRateShifts INSTANCE = new NoExchangeRateShifts();

    private NoExchangeRateShifts() {
    }

    @Override
    public ExchangeRates shift(final ExchangeRates rates) {
        return rates;
    }

    @Override
    public <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shift(final ExchangeRate<F, T> rate) {
        return rate;
    }

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder();
    }

}
