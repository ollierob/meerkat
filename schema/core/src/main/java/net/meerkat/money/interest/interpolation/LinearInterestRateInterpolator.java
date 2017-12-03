package net.meerkat.money.interest.interpolation;

import net.meerkat.numeric.interpolation.FirstOrderInterpolator;
import net.meerkat.numeric.percentage.Percentage;
import net.meerkat.temporal.date.count.DayCount;
import org.apache.commons.math3.fraction.Fraction;

import java.time.LocalDate;

/**
 *
 * @author ollie
 */
public class LinearInterestRateInterpolator
        implements InterestRateInterpolator, FirstOrderInterpolator<LocalDate, Percentage> {

    private final DayCount dayCount;

    public LinearInterestRateInterpolator(final DayCount dayCount) {
        this.dayCount = dayCount;
    }

    @Override
    public DayCount dayCount() {
        return dayCount;
    }

    @Override
    public Percentage interpolate(
            final LocalDate key,
            final LocalDate floorKey,
            final LocalDate ceilingKey,
            final Percentage floorValue,
            final Percentage ceilingValue) {
        final Fraction xf = new Fraction(dayCount.daysBetween(floorKey, key), dayCount.daysBetween(floorKey, ceilingKey));
        final Percentage yf = ceilingValue.minus(floorValue);
        return floorValue.plus(yf.times(xf));
    }

    @Override
    public Percentage extrapolateLeft(final LocalDate key, final LocalDate ceilingKey, final Percentage ceilingValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Percentage extrapolateRight(final LocalDate key, final LocalDate floorKey, Percentage floorValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
