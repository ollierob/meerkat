package net.meerkat.pricing.shifts;

import java.util.Map;

import net.meerkat.money.interest.InterestRate;
import net.ollie.goat.numeric.percentage.Percentage;

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
