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

    class UnknownInterestRateException extends RuntimeException implements HasInterestRateId {

        private static final long serialVersionUID = 1L;

        private final InterestRateId id;

        public UnknownInterestRateException(final InterestRateId id) {
            super("Unknown rate: " + id);
            this.id = id;
        }

        @Override
        public InterestRateId interestRateId() {
            return id;
        }

    }

}
