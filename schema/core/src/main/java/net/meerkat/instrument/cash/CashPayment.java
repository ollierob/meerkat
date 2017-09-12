package net.meerkat.instrument.cash;

import java.time.LocalDate;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.interest.fixed.FixedInterestRate;

/**
 *
 * @author ollie
 */
public interface CashPayment<C extends CurrencyId> extends HasCurrencyId {

    @Nonnull
    LocalDate paymentDate();

    @Nonnull
    Money<C> paymentAmount();

    @Override
    default C currencyId() {
        return this.paymentAmount().currencyId();
    }

    @CheckReturnValue
    default <T extends CurrencyId> CashPayment<T> convert(final ExchangeRate<C, T> exchangeRate) {
        return of(this.paymentDate(), exchangeRate.convert(this.paymentAmount()));
    }

    default <T extends CurrencyId> CashPayment<T> convert(final T toCurrency, final ExchangeRates exchangeRates) {
        return this.convert(exchangeRates.rate(this.currencyId(), toCurrency));
    }

    @Nonnull
    default Money<C> accrue(final FixedInterestRate rate, final LocalDate date) {
        return rate.accrue(this.paymentAmount(), this.paymentDate(), date);
    }

    static <C extends CurrencyId> CashPayment<C> of(final LocalDate date, final Money<C> amount) {
        return new DefaultCashPayment<>(date, amount);
    }

}
