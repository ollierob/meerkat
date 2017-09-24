package net.meerkat.instrument.interest.swap;

import java.time.LocalDate;

import net.coljate.list.List;
import net.coljate.set.MutableSet;
import net.meerkat.Explainable;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.identifier.currency.HasCurrencyIds;
import net.meerkat.instrument.derivative.swap.Swap;
import net.meerkat.instrument.interest.InterestRateDerivative;
import net.meerkat.instrument.interest.swap.leg.InterestRateSwapLeg;

/**
 *
 * @author ollie
 */
public interface InterestRateSwap
        extends InterestRateDerivative, Swap, HasCurrencyIds, Explainable {

    @Override
    List<? extends InterestRateSwapLeg<?, ?>> legs();

    default List<? extends InterestRateSwapLeg<?, ?>> legsAfter(final LocalDate date) {
        return this.legs().filter(leg -> leg.payDate().isAfter(date));
    }

    @Override
    default CurrencyIds currencyIds() {
        final MutableSet<CurrencyId> currencies = MutableSet.createHashSet(2);
        for (final InterestRateSwapLeg<?, ?> leg : this.legs()) {
            currencies.add(leg.payCurrency());
            currencies.add(leg.receiveCurrency());
        }
        return CurrencyIds.of(currencies);
    }

    default boolean isCrossCurrency() {
        return this.currencyIds().count() >= 2;
    }

    @Override
    default <R> R handleWith(final InterestRateDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
