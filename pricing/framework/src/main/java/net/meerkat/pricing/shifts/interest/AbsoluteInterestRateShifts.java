package net.meerkat.pricing.shifts.interest;

import net.meerkat.money.interest.InterestRate;
import net.meerkat.numeric.percentage.Percentage;

import java.util.Map;

/**
 *
 * @author ollie
 */
public class AbsoluteInterestRateShifts implements InterestRateShifts {

    private final Percentage shift;

    protected AbsoluteInterestRateShifts(final Percentage shift) {
        this.shift = shift;
    }

    @Override
    public InterestRate shift(final InterestRate rate) {
        return rate.plus(shift);
    }

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder().put("shift", shift);
    }

}
