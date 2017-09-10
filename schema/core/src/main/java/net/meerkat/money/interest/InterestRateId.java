package net.meerkat.money.interest;

/**
 *
 * @author Ollie
 */
public interface InterestRateId extends InterestRateOrId {

    @Override
    default InterestRate resolve(final InterestRateProvider provider) throws UnknownInterestRateException {
        return provider.require(this);
    }

    static InterestRateId named(final String name) {
        return new NamedInterestRateId(name);
    }

}
