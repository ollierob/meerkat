package net.meerkat.pricing.shifts;

import javax.annotation.Nonnull;

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

    default ExchangeRates shift(@Nonnull final ExchangeRates rates) {
        return new ShiftedExchangeRates(rates, this);
    }

    static ExchangeRateShifts none() {
        return NoExchangeRateShifts.INSTANCE;
    }

    @Nonnull
    static ExchangeRateShifts cast(final SecurityShifts shifts) {
        return shifts.as(ExchangeRateShifts.class).orElseGet(ExchangeRateShifts::none);
    }

    interface ExchangeRateShifter {

        default <C extends CurrencyId, R extends CurrencyId> Money<C> shift(final Money<R> amount, final ExchangeRateShifts shifts, final C reportingCurrency, final ExchangeRates exchangeRates) {
            final ExchangeRate<R, C> baseRate = exchangeRates.rate(amount.currencyId(), reportingCurrency);
            final ExchangeRate<R, C> shiftedRate = shifts.shift(baseRate);
            return shiftedRate.convert(amount);
        }

    }

}
