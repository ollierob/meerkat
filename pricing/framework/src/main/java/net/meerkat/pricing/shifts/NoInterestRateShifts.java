package net.meerkat.pricing.shifts;

import java.util.Map;

import net.meerkat.money.interest.InterestRate;

/**
 *
 * @author Ollie
 */
class NoInterestRateShifts implements InterestRateShifts {

    static final NoInterestRateShifts INSTANCE = new NoInterestRateShifts();

    private NoInterestRateShifts() {
    }

    @Override
    public InterestRate shift(InterestRate rate) {
        return rate;
    }

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder();
    }

}
