package net.ollie.meerkat.numeric.interest.interpolation;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.meerkat.utils.numeric.Percentage;
import net.ollie.meerkat.time.daycount.AccrualFactor;
import net.ollie.meerkat.time.daycount.DayCount;
import net.ollie.meerkat.utils.numeric.interpolation.Interpolator;

/**
 *
 * @author ollie
 */
public interface InterestRateInterpolator extends Interpolator<LocalDate, Percentage> {

    @Nonnull
    DayCount dayCount();

    InterestRateInterpolator LINEAR_ACT_365 = new LinearInterestRateInterpolator(AccrualFactor.ACT_365);
    InterestRateInterpolator DEFAULT = LINEAR_ACT_365;

}
