package net.meerkat.pricing.interest;

import java.time.temporal.Temporal;

import net.meerkat.instrument.interest.swap.InterestRateSwap;

/**
 *
 * @author Ollie
 */
public interface InterestRateSwapPricer<T extends Temporal>
        extends InterestRateDerivativePricer<T, InterestRateSwap> {

}
