package net.ollie.meerkat.numeric.interest;

import java.util.function.Function;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface HasInterestRateId {

    @Nonnull
    InterestRateId interestRateId();

    default InterestRate interestRate(final Function<? super InterestRateId, ? extends InterestRate> toRate) {
        return toRate.apply(this.interestRateId());
    }

}