package net.meerkat.money.interest.interpolation;

import net.meerkat.numeric.decimal.BigDecimalFraction;
import net.meerkat.numeric.interpolation.LinearDecimalInterpolator;
import net.meerkat.numeric.percentage.Percentage;
import net.meerkat.temporal.date.Periods;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.Period;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class LinearYieldCurveInterpolator extends LinearDecimalInterpolator<Period, Percentage> {

    @Override
    public BigDecimal numerical(final Period tenor) {
        return BigDecimal.valueOf(Periods.approximateLength(tenor));
    }

    @Override
    public Percentage multiply(final Percentage value, final BigDecimalFraction multiplier) {
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
