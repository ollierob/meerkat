package net.meerkat.security;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.meerkat.money.currency.Currency;
import net.meerkat.money.currency.HasCurrency;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public interface CashPayment<C extends Currency> extends HasCurrency {

    @Nonnull
    LocalDate date();

    @Nonnull
    Money<C> amount();

    @Override
    default C currency() {
        return this.amount().currency();
    }

    static <C extends Currency> CashPayment<C> of(final LocalDate date, final Money<C> amount) {
        return new DefaultCashPayment<>(date, amount);
    }

}
