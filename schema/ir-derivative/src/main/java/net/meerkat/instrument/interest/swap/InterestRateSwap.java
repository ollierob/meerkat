package net.meerkat.instrument.interest.swap;

import net.meerkat.instrument.interest.swap.leg.InterestRateSwapLeg;
import net.coljate.list.List;
import net.coljate.set.MutableSet;
import net.meerkat.Explainable;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.identifier.currency.HasCurrencyIds;
import net.meerkat.instrument.derivative.swap.Swap;
import net.meerkat.instrument.interest.InterestRateDerivative;

/**
 *
 * @author ollie
 */
public interface InterestRateSwap
        extends InterestRateDerivative, Swap, HasCurrencyIds, Explainable {

    @Override
    List<? extends InterestRateSwapLeg<?, ?>> legs();

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
        return this.currencyIds().size() >= 2;
    }

    @Override
    default <R> R handleWith(final Handler<R> handler) {
        return handler.handle(this);
    }

}
