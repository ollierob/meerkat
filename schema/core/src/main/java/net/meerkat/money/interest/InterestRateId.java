package net.meerkat.money.interest;

import net.meerkat.money.interest.exception.UnknownInterestRateException;

/**
 *
 * @author Ollie
 */
public interface InterestRateId extends InterestRateOrId {

    @Override
    default InterestRate resolve(final InterestRateSnapshot provider) throws UnknownInterestRateException {
        return provider.require(this);
    }

    static InterestRateId named(final String name) {
        return new NamedInterestRateId(name);
    }

}
