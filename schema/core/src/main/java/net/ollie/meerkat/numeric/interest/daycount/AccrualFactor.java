package net.ollie.meerkat.numeric.interest.daycount;

/**
 *
 * @author Ollie
 */
public interface AccrualFactor extends DayCount, YearCount {

    AccrualFactor ACT_ACT = ActualActualAccrualFactor.ACT_ACT;

}
