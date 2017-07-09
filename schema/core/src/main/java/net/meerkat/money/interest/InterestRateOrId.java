package net.meerkat.money.interest;

import javax.annotation.Nonnull;

import net.meerkat.money.interest.InterestRateProvider.UnknownInterestRateException;

/**
 *
 * @author ollie
 */
public interface InterestRateOrId {

    @Nonnull
    InterestRate resolve(InterestRateProvider provider) throws UnknownInterestRateException;
    
}
