package net.meerkat.money.interest.curve;

import java.time.Period;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.Dates;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class TenorYieldCurve extends AbstractYieldCurve<Period, TenorYieldCurve> {

    public TenorYieldCurve(final Map<Period, Percentage> curve) {
        super(curve, Dates.APPROXIMATE_PERIOD_COMPARATOR);
    }

    @Override
    protected TenorYieldCurve toCurve(final Map<Period, Percentage> curve) {
        return new TenorYieldCurve(curve);
    }

}
