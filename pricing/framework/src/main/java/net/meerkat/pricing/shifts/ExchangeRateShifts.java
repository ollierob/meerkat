package net.meerkat.pricing.shifts;

import java.util.Map;
import java.util.Optional;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.fx.ExchangeRates;

/**
 *
 * @author Ollie
 */
public interface ExchangeRateShifts extends SecurityShifts {

    <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shift(ExchangeRate<F, T> rate);

    default ExchangeRates shift(final ExchangeRates rates) {
        return new ShiftedExchangeRates(rates, this);
    }

    interface ExchangeRateShifter {

        default <C extends CurrencyId, R extends CurrencyId> Money<C> shift(final Money<R> amount, final ExchangeRateShifts shifts, final C reportingCurrency, final ExchangeRates exchangeRates) {
            final ExchangeRate<R, C> baseRate = exchangeRates.rate(amount.currencyId(), reportingCurrency);
            final ExchangeRate<R, C> shiftedRate = shifts.shift(baseRate);
            return shiftedRate.convert(amount);
        }

    }

    ExchangeRateShifts NONE = new ExchangeRateShifts() {

        @Override
        public <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shift(final ExchangeRate<F, T> rate) {
            return rate;
        }

        @Override
        public Map<String, Object> explain() {
            return this.explanationBuilder();
        }

    };

    class ShiftedExchangeRates implements ExchangeRates {

        private final ExchangeRates baseRates;
        private final ExchangeRateShifts shifts;

        public ShiftedExchangeRates(ExchangeRates baseRates, ExchangeRateShifts shifts) {
            this.baseRates = baseRates;
            this.shifts = shifts;
        }

        @Override
        public <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> rate(final F from, final T to) throws UnavailableExchangeRate {
            return shifts.shift(baseRates.rate(from, to));
        }

        @Override
        public <F extends CurrencyId, T extends CurrencyId> Optional<ExchangeRate<F, T>> maybeRate(final F from, final T to) {
            return baseRates.maybeRate(from, to).map(shifts::shift);
        }

    }

}
