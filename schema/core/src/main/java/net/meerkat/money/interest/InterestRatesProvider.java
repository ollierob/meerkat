package net.meerkat.money.interest;

import net.meerkat.money.interest.exception.UnavailableInterestRateException;
import net.ollie.goat.data.Provider;

/**
 *
 * @author Ollie
 */
public interface InterestRatesProvider<T> extends Provider<T, InterestRates> {

    @Override
    default InterestRates require(final T key) throws UnavailableInterestRateException {
        return this.require(key, UnavailableInterestRateException::new);
    }

}
