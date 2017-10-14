package net.meerkat.money.interest;

import javax.annotation.Nonnull;

import net.meerkat.money.interest.exception.InterestRateException;
import net.meerkat.money.interest.exception.UnavailableInterestRateException;
import net.ollie.goat.data.CompositeProvider;

/**
 *
 * @author Ollie
 */
public interface InterestRateProvider<T> extends CompositeProvider<T, InterestRateId, InterestRate, InterestRateSnapshot> {

    @Override
    default InterestRateSnapshot require(final T key) throws UnavailableInterestRateException {
        return this.require(key, UnavailableInterestRateException::new);
    }

    @Nonnull
    @Override
    default InterestRate require(final T temporal, final InterestRateId interestRateId) throws InterestRateException {
        return CompositeProvider.super.require(temporal, interestRateId);
    }

}
