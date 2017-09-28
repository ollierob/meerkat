package net.meerkat.instrument.interest.swap;

import net.meerkat.Explainable;
import net.meerkat.identifier.currency.HasCurrencyIds;
import net.meerkat.instrument.derivative.swap.Swap;
import net.meerkat.instrument.derivative.swap.SwapLegs;
import net.meerkat.instrument.interest.InterestRateDerivative;
import net.meerkat.instrument.interest.swap.leg.InterestRateSwapLeg;

/**
 *
 * @author ollie
 */
public interface InterestRateSwap
        extends InterestRateDerivative, Swap, HasCurrencyIds, Explainable {

    @Override
    SwapLegs<? extends InterestRateSwapLeg<?, ?>> legs();

    default boolean isCrossCurrency() {
        return this.currencyIds().count() >= 2;
    }

    @Override
    default <R> R handleWith(final InterestRateDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
