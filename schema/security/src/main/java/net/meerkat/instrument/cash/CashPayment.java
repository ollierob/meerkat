package net.meerkat.instrument.cash;

import java.time.LocalDate;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.identifier.currency.HasCurrencyId;

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
    <T extends CurrencyId> CashPayment<T> convert(ExchangeRate<C, T> exchangeRate);

    static <C extends CurrencyId> CashPayment<C> of(final LocalDate date, final Money<C> amount) {
        return new DefaultCashPayment<>(date, amount);
    }

}
