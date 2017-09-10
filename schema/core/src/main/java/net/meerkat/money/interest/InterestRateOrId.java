package net.meerkat.money.interest;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface InterestRateOrId {

    @Nonnull
    InterestRate resolve(InterestRateProvider provider) throws UnknownInterestRateException;

}
