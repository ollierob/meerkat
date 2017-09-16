package net.meerkat.money.interest;

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
    default InterestRate resolve(final InterestRates provider) throws UnknownInterestRateException {
        return provider.require(this.interestRateId());
    }

}
