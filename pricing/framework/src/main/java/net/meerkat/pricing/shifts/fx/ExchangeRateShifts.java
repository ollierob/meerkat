package net.meerkat.pricing.shifts.fx;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;
import net.meerkat.money.fx.ExchangeRateSnapshot;

/**
 *
 * @author Ollie
 */
public interface ExchangeRateShifts extends InstrumentPriceShifts {

    <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shift(ExchangeRate<F, T> rate);

    default ExchangeRateSnapshot shift(@Nonnull final ExchangeRateSnapshot rates) {
        return new ShiftedExchangeRates(rates, this);
    }

    static ExchangeRateShifts none() {
        return NoExchangeRateShifts.INSTANCE;
    }

    @Nonnull
    static ExchangeRateShifts cast(final InstrumentPriceShifts shifts) {
        return shifts.as(ExchangeRateShifts.class).orElseGet(ExchangeRateShifts::none);
    }

    interface ExchangeRateShifter {

        default <C extends CurrencyId, R extends CurrencyId> Money<C> shift(final Money<R> amount, final ExchangeRateShifts shifts, final C reportingCurrency, final ExchangeRateSnapshot exchangeRates) {
            final ExchangeRate<R, C> baseRate = exchangeRates.rate(amount.currencyId(), reportingCurrency);
            final ExchangeRate<R, C> shiftedRate = shifts.shift(baseRate);
            return shiftedRate.convertAtMid(amount);
        }

    }

}
