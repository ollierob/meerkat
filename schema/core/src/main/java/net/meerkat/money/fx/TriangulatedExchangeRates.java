package net.meerkat.money.fx;

import java.util.Optional;
import java.util.function.UnaryOperator;

import net.meerkat.money.currency.Currency;
import net.ollie.goat.optionals.Optionals;

/**
 *
 * @author ollie
 */
public class TriangulatedExchangeRates implements ExchangeRates {

    private final ExchangeRates baseRates;
    private final UnaryOperator<Currency> triangulation;

    public TriangulatedExchangeRates(final ExchangeRates baseRates, final UnaryOperator<Currency> triangulation) {
        this.baseRates = baseRates;
        this.triangulation = triangulation;
    }

    @Override
    public <F extends Currency, T extends Currency> Optional<ExchangeRate<F, T>> maybeRate(final F from, final T to) {
        return Optionals.firstPresent(
                baseRates.maybeRate(from, to),
                () -> this.triangulate(from, triangulation.apply(from), to));
    }

    private <F extends Currency, X extends Currency, T extends Currency> Optional<ExchangeRate<F, T>> triangulate(
            final F from,
            final X triangulate,
            final T to) {
        final Optional<ExchangeRate<F, X>> first = baseRates.maybeRate(from, triangulate);
        final Optional<ExchangeRate<X, T>> second = first.isPresent()
                ? baseRates.maybeRate(triangulate, to)
                : Optional.empty();
        return second.isPresent()
                ? first.map(rate -> rate.triangulate(second.get()))
                : Optional.empty();
    }

}
