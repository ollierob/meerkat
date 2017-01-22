package net.meerkat.money.fx;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;

import net.meerkat.money.FractionalMoney;
import net.meerkat.money.Money;
import net.meerkat.money.currency.Currency;
import net.meerkat.money.currency.HasCurrencies;
import net.ollie.goat.numeric.fraction.DecimalFraction;

/**
 *
 * @author Ollie
 */
public interface ExchangeRate<F extends Currency, T extends Currency>
        extends Comparable<ExchangeRate<F, T>>, HasCurrencies {

    @Nonnull
    F from();

    @Nonnull
    T to();

    @Nonnull
    DecimalFraction rate();

    default Money<T> convert(final Money<F> from) {
        return new FractionalMoney<>(this.to(), this.rate().times(from.amount()));
    }

    default Money<F> convertFrom(final Money<T> from) {
        return new FractionalMoney<>(this.from(), this.rate().over(from.amount()));
    }

    default ExchangeRate<T, F> inverse() {
        return new InverseExchangeRate<>(this);
    }

    default <X extends Currency> ExchangeRate<F, X> triangulate(final ExchangeRate<T, X> that) {
        return new TriangulatedExchangeRate<>(this, that);
    }

    @Override
    default int compareTo(final ExchangeRate<F, T> that) {
        return this.rate().compareTo(that.rate());
    }

    @Override
    default Set<? extends Currency> currencies() {
        final Set<Currency> currencies = new HashSet<>(2);
        currencies.add(this.from());
        currencies.add(this.to());
        return currencies;
    }

}
