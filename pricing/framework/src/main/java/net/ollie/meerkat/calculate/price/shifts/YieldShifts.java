package net.ollie.meerkat.calculate.price.shifts;

import javax.annotation.Nonnull;

import net.ollie.meerkat.numeric.interest.curve.YieldCurve;

/**
 *
 * @author Ollie
 */
public interface YieldShifts extends SecurityShifts {

    @Nonnull
    YieldCurve shiftYieldCurve(@Nonnull YieldCurve curve);

}
