package net.meerkat.instrument.cash;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.fx.ExchangeRateSnapshot;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.meerkat.temporal.date.HasDate;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.Optional;

/**
 * @author ollie
 */
public interface CashPayment<C extends CurrencyId> extends HasCurrencyId, HasDate {

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
        return of(this.paymentDate(), exchangeRate.convertAtMid(this.paymentAmount()));
    }

    default <T extends CurrencyId> CashPayment<T> convert(final T toCurrency, final ExchangeRateSnapshot exchangeRates) {
        return this.convert(exchangeRates.rate(this.currencyId(), toCurrency));
    }

    @Nonnull
    default Money<C> accrue(final FixedInterestRate rate, final LocalDate date) {
        return rate.accrue(this.paymentAmount(), this.paymentDate(), date);
    }

    default boolean isZero() {
        return this.paymentAmount().isZero();
    }

    @Nonnull
    @Override
    default LocalDate date() {
        return this.paymentDate();
    }

    static <C extends CurrencyId> CashPayment<C> of(final LocalDate date, final Money<C> amount) {
        return new DefaultCashPayment<>(date, amount);
    }

    static <C extends CurrencyId> Optional<CashPayment<C>> ofNonZero(final LocalDate date, final Money<C> amount) {
        return amount.isZero()
                ? Optional.empty()
                : Optional.of(of(date, amount));
    }

}
