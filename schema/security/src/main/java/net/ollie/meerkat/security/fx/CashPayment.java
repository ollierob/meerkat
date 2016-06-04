package net.ollie.meerkat.security.fx;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.goat.currency.CurrencyId;
import net.ollie.goat.currency.HasCurrencyId;
import net.ollie.goat.money.Money;

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

    static <C extends CurrencyId> CashPayment<C> of(final LocalDate date, final Money<C> amount) {
        return new DefaultCashPayment<>(date, amount);
    }

}
