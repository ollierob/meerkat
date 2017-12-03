package net.meerkat.pricing.shifts.interest;

import net.meerkat.money.interest.InterestRate;
import net.meerkat.numeric.percentage.Percentage;

import java.util.Map;

/**
 *
 * @author ollie
 */
public class RelativeInterestRateShifts implements InterestRateShifts {

    private final Percentage shift;

    protected RelativeInterestRateShifts(final Percentage shift) {
        this.shift = shift;
    }

    @Override
    public InterestRate shift(final InterestRate rate) {
        return rate.times(shift);
    }

    @Override
    public Map<String, Object> explain() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
