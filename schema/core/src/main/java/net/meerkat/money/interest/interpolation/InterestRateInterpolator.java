package net.meerkat.money.interest.interpolation;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.count.DayCount;
import net.ollie.goat.numeric.interpolation.Interpolator;
import net.ollie.goat.temporal.date.count.DateArithmetic;

/**
 *
 * @author ollie
 */
public interface InterestRateInterpolator extends Interpolator<LocalDate, Percentage> {

    @Nonnull
    DayCount dayCount();

    InterestRateInterpolator LINEAR_ACT_365 = new LinearInterestRateInterpolator(DateArithmetic.ACT_365);
    InterestRateInterpolator DEFAULT = LINEAR_ACT_365;

}
