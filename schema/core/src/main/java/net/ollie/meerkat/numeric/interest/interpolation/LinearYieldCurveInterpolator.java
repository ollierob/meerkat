package net.ollie.meerkat.numeric.interest.interpolation;

import java.math.BigDecimal;

import net.ollie.goat.numeric.fraction.DecimalFraction;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.meerkat.numeric.interest.curve.Tenor;

/**
 *
 * @author ollie
 */
public class LinearYieldCurveInterpolator extends LinearDecimalInterpolator<Tenor, Percentage> {

    @Override
    public BigDecimal numerical(final Tenor tenor) {
        return tenor.decimalValue();
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
