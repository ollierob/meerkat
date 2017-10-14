package net.meerkat.money.interest;

import net.meerkat.money.interest.exception.UnknownInterestRateException;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface InterestRateOrId {

    @Nonnull
    InterestRate resolve(InterestRateSnapshot provider) throws UnknownInterestRateException;

}
