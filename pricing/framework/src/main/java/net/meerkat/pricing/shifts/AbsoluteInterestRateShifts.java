package net.meerkat.pricing.shifts;

import java.util.Map;

import net.meerkat.money.interest.InterestRate;
import net.ollie.goat.numeric.percentage.Percentage;

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
