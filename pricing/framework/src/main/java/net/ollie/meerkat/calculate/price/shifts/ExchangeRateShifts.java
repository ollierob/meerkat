package net.ollie.meerkat.calculate.price.shifts;

import net.ollie.meerkat.calculate.fx.ExchangeRateCalculator;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.fx.ExchangeRate;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author Ollie
 */
public interface ExchangeRateShifts extends SecurityShifts {

    <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shiftExchangeRate(ExchangeRate<F, T> rate);

    interface ExchangeRateShifter {

        default <C extends CurrencyId, R extends CurrencyId> Money<C> shiftFx(final Money<R> amount, final ExchangeRateShifts shifts, final C reportingCurrency, final ExchangeRateCalculator exchangeRates) {
            final ExchangeRate<R, C> baseRate = exchangeRates.rate(amount.currencyId(), reportingCurrency);
            final ExchangeRate<R, C> shiftedRate = shifts.shiftExchangeRate(baseRate);
            return shiftedRate.convert(amount);
        }

    }

}
