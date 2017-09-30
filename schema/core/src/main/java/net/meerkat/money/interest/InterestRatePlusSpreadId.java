package net.meerkat.money.interest;

import net.meerkat.money.interest.exception.UnknownInterestRateException;

import javax.annotation.Nonnull;

import net.ollie.goat.numeric.percentage.Percentage;

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
    default InterestRate resolve(final InterestRates provider) throws UnknownInterestRateException {
        return this.base().resolve(provider).plus(this.spread());
    }

}
