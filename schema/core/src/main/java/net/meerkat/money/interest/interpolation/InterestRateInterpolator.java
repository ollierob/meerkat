package net.meerkat.money.interest.interpolation;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.goat.numeric.interpolation.Interpolator;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.count.DateArithmetic;
import net.ollie.goat.temporal.date.count.DayCount;

/**
 *
 * @author ollie
 */
public interface InterestRateInterpolator extends Interpolator<LocalDate, Percentage> {

    @Nonnull
    DayCount dayCount();

    static InterestRateInterpolator linear(final DayCount arithmetic) {
        return new LinearInterestRateInterpolator(arithmetic);
    }

    InterestRateInterpolator LINEAR_ACT_360 = linear(DateArithmetic.ACT_360);
    InterestRateInterpolator LINEAR_ACT_365 = linear(DateArithmetic.ACT_365);

}
