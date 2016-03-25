package net.ollie.meerkat.calculate.fx;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.ExchangeRate;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author Ollie
 */
public interface ExchangeRateCalculator {

    <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> rate(F from, T to);

    @Nonnull
    default <F extends CurrencyId, T extends CurrencyId> Money<T> convert(@Nonnull final Money<F> money, @Nonnull final T into) {
        return this.rate(money.currency(), into).convertTo(money);
    }

    @Nonnull
    default <F extends CurrencyId, T extends CurrencyId> Money<F> add(final Money<F> left, final Money<T> right) {
        return left.plus(this.convert(right, left.currency()));
    }

    @Nonnull
    default <F extends CurrencyId, T extends CurrencyId> Money<F> subtract(final Money<F> minuend, final Money<T> subtrahend) {
        return minuend.minus(this.convert(subtrahend, minuend.currency()));
    }

}
