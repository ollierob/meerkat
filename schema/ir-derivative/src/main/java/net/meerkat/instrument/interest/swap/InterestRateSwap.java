package net.meerkat.instrument.interest.swap;

import net.meerkat.instrument.derivative.swap.Swap;
import net.meerkat.instrument.interest.InterestRateDerivative;
import net.meerkat.utils.collections.sequence.Sequence;

/**
 *
 * @author ollie
 */
public interface InterestRateSwap extends InterestRateDerivative, Swap {

    @Override
    Sequence<? extends InterestRateSwapLeg<?, ?>> legs();

}
