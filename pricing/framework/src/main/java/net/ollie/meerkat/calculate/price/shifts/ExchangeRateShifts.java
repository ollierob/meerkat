package net.ollie.meerkat.calculate.price.shifts;

import net.meerkat.money.currency.Currency;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.ollie.meerkat.calculate.fx.ExchangeRates;

/**
 *
 * @author Ollie
 */
public interface ExchangeRateShifts extends SecurityShifts {

    <F extends Currency, T extends Currency> ExchangeRate<F, T> shift(ExchangeRate<F, T> rate);

    interface ExchangeRateShifter {

        default <C extends Currency, R extends Currency> Money<C> shift(final Money<R> amount, final ExchangeRateShifts shifts, final C reportingCurrency, final ExchangeRates exchangeRates) {
            final ExchangeRate<R, C> baseRate = exchangeRates.rate(amount.currency(), reportingCurrency);
            final ExchangeRate<R, C> shiftedRate = shifts.shift(baseRate);
            return shiftedRate.convert(amount);
        }

    }

}
