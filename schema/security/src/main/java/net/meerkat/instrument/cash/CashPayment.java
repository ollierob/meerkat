package net.meerkat.instrument.cash;

import java.time.LocalDate;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.money.currency.CurrencyId;
import net.meerkat.money.currency.HasCurrency;
import net.meerkat.money.fx.ExchangeRate;

/**
 *
 * @author ollie
 */
public interface CashPayment<C extends CurrencyId> extends HasCurrency {

    @Nonnull
    LocalDate date();

    @Nonnull
    Money<C> amount();

    @Override
    default C currency() {
        return this.amount().currency();
    }

    @CheckReturnValue
    <T extends CurrencyId> CashPayment<T> convert(ExchangeRate<C, T> exchangeRate);

    static <C extends CurrencyId> CashPayment<C> of(final LocalDate date, final Money<C> amount) {
        return new DefaultCashPayment<>(date, amount);
    }

}
