package net.ollie.meerkat.calculate.price.shifts;

import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author Ollie
 */
public interface ExchangeRateShifts extends SecurityShifts {

    <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shift(ExchangeRate<F, T> rate);

    interface ExchangeRateShifter {

        default <C extends CurrencyId, R extends CurrencyId> Money<C> shift(final Money<R> amount, final ExchangeRateShifts shifts, final C reportingCurrency, final ExchangeRates exchangeRates) {
            final ExchangeRate<R, C> baseRate = exchangeRates.rate(amount.currencyId(), reportingCurrency);
            final ExchangeRate<R, C> shiftedRate = shifts.shift(baseRate);
            return shiftedRate.convert(amount);
        }

    }

}
