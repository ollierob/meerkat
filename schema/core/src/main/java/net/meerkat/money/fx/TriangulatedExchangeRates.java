package net.meerkat.money.fx;

import net.meerkat.functions.optionals.Optionals;
import net.meerkat.identifier.currency.CurrencyId;

import java.util.Optional;
import java.util.function.UnaryOperator;

/**
 *
 * @author ollie
 */
public class TriangulatedExchangeRates implements ExchangeRateSnapshot {

    private final ExchangeRateSnapshot baseRates;
    private final UnaryOperator<CurrencyId> triangulation;

    public TriangulatedExchangeRates(final ExchangeRateSnapshot baseRates, final UnaryOperator<CurrencyId> triangulation) {
        this.baseRates = baseRates;
        this.triangulation = triangulation;
    }

    @Override
    public <F extends CurrencyId, T extends CurrencyId> Optional<ExchangeRate<F, T>> maybeRate(final F from, final T to) {
        return Optionals.firstPresent(
                baseRates.maybeRate(from, to),
                () -> this.triangulate(from, triangulation.apply(from), to));
    }

    private <F extends CurrencyId, X extends CurrencyId, T extends CurrencyId> Optional<ExchangeRate<F, T>> triangulate(
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
