package net.meerkat.money.interest;

import net.meerkat.money.interest.InterestRateProvider.UnknownInterestRateException;

/**
 *
 * @author Ollie
 */
public interface InterestRateId extends InterestRateOrId {

    @Override
    default InterestRate resolve(final InterestRateProvider provider) throws UnknownInterestRateException {
        return provider.require(this, UnknownInterestRateException::new);
    }

    static InterestRateId named(final String name) {
        return new NamedInterestRateId(name);
    }

}
