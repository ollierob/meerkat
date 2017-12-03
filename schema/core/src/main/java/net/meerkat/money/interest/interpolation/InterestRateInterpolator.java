package net.meerkat.money.interest.interpolation;

import net.meerkat.numeric.interpolation.Interpolator;
import net.meerkat.numeric.percentage.Percentage;
import net.meerkat.temporal.date.count.DateArithmetic;
import net.meerkat.temporal.date.count.DayCount;

import javax.annotation.Nonnull;
import java.time.LocalDate;

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
