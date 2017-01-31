package net.meerkat.money.interest.interpolation;

import java.math.BigDecimal;
import java.time.Period;

import net.ollie.goat.numeric.fraction.DecimalFraction;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.Dates;

/**
 *
 * @author ollie
 */
public class LinearYieldCurveInterpolator extends LinearDecimalInterpolator<Period, Percentage> {

    @Override
    public BigDecimal numerical(final Period tenor) {
        return BigDecimal.valueOf(Dates.approximateLength(tenor));
    }

    @Override
    public Percentage multiply(final Percentage value, final DecimalFraction multiplier) {
        return value.times(multiplier);
    }

    @Override
    public Percentage negate(final Percentage value) {
        return value.negate();
    }

    @Override
    public Percentage add(final Percentage left, final Percentage right) {
        return left.plus(right);
    }

}
