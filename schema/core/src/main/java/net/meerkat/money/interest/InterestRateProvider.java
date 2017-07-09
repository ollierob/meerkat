package net.meerkat.money.interest;

import net.ollie.goat.data.Provider;

/**
 *
 * @author ollie
 */
public interface InterestRateProvider extends Provider<InterestRateId, InterestRate> {

    @Override
    default InterestRate require(final InterestRateId key) throws UnknownInterestRateException {
        return this.require(key, UnknownInterestRateException::new);
    }

    class UnknownInterestRateException extends RuntimeException {

    }

}
