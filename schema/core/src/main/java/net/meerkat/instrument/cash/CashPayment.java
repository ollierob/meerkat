package net.meerkat.instrument.cash;

import java.time.LocalDate;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.fx.ExchangeRates;

/**
 *
 * @author ollie
 */
public interface CashPayment<C extends CurrencyId> extends HasCurrencyId {

    @Nonnull
    LocalDate date();

    @Nonnull
    Money<C> amount();

    @Override
    default C currencyId() {
        return this.amount().currencyId();
    }

    @CheckReturnValue
    default <T extends CurrencyId> CashPayment<T> convert(final ExchangeRate<C, T> exchangeRate) {
        return of(this.date(), exchangeRate.convert(this.amount()));
    }

    default <T extends CurrencyId> CashPayment<T> convert(final T toCurrency, final ExchangeRates exchangeRates) {
        return this.convert(exchangeRates.rate(this.currencyId(), toCurrency));
    }

    static <C extends CurrencyId> CashPayment<C> of(final LocalDate date, final Money<C> amount) {
        return new DefaultCashPayment<>(date, amount);
    }

}
