package net.meerkat.money.interest;

import net.meerkat.money.interest.exception.UnknownInterestRateException;
import net.meerkat.numeric.percentage.Percentage;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface InterestRatePlusSpreadId extends InterestRateId {

    @Nonnull
    Percentage spread();

    @Nonnull
    InterestRateId base();

    @Override
    default InterestRate resolve(final InterestRateSnapshot provider) throws UnknownInterestRateException {
        return this.base().resolve(provider).plus(this.spread());
    }

}
