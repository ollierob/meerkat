package net.ollie.meerkat.numeric.interest.interpolation;

import java.time.LocalDate;

import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.daycount.AccrualFactor;
import net.ollie.meerkat.numeric.interest.daycount.DayCount;
import net.ollie.meerkat.utils.numeric.interpolation.Interpolator;

/**
 *
 * @author ollie
 */
public interface InterestRateInterpolator extends Interpolator<LocalDate, Percentage> {
    
    DayCount dayCount();
    
    LinearInterestRateInterpolator LINEAR_ACT_ACT = new LinearInterestRateInterpolator(AccrualFactor.ACT_ACT);
    InterestRateInterpolator DEFAULT = LINEAR_ACT_ACT;

}
