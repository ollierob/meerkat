package net.meerkat.money.interest;

import net.meerkat.money.interest.exception.UnknownInterestRateException;
import net.ollie.goat.data.Provider;

/**
 *
 * @author ollie
 */
public interface InterestRates extends Provider<InterestRateId, InterestRate> {

    @Override
    default InterestRate require(final InterestRateId key) throws UnknownInterestRateException {
        return this.require(key, UnknownInterestRateException::new);
    }

}
