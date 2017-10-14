package net.meerkat.money.interest;

import net.meerkat.money.interest.exception.UnknownInterestRateException;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface HasInterestRateId {

    @Nonnull
    InterestRateId interestRateId();

    @CheckForNull
    default InterestRate resolve(final InterestRateSnapshot provider) throws UnknownInterestRateException {
        return provider.require(this.interestRateId());
    }

}
