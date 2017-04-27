package net.meerkat.money.interest;

/**
 *
 * @author Ollie
 */
public interface InterestRateId {

    static InterestRateId named(final String name) {
        return new NamedInterestRateId(name);
    }

}
