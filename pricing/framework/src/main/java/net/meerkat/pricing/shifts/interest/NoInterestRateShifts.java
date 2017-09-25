package net.meerkat.pricing.shifts.interest;

import java.util.Map;

import net.meerkat.money.interest.InterestRate;

/**
 *
 * @author Ollie
 */
public interface NoInterestRateShifts extends InterestRateShifts {

    NoInterestRateShifts INSTANCE = new NoInterestRateShifts() {

        @Override
        public Map<String, Object> explain() {
            return this.explanationBuilder();
        }

    };

    @Override
    default InterestRate shift(final InterestRate rate) {
        return rate;
    }

    @Override
    default Map<String, Object> explain() {
        return this.explanationBuilder();
    }

}
