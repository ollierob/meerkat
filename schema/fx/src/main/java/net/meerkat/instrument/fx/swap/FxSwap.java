package net.meerkat.instrument.fx.swap;

import net.meerkat.instrument.derivative.swap.Swap;
import net.meerkat.instrument.derivative.swap.SwapLegs;
import net.meerkat.instrument.fx.FxDerivative;

import javax.annotation.Nonnull;

public interface FxSwap extends Swap, FxDerivative {

    @Nonnull
    @Override
    SwapLegs<FxSwapLeg<?, ?>> legs();

}
