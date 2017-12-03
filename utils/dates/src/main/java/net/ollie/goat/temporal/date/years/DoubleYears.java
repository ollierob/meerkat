package net.ollie.goat.temporal.date.years;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Period;

/**
 * @author Ollie
 */
@XmlRootElement
public class DoubleYears implements Years {

    private static final long serialVersionUID = 1L;

    @XmlValue
    private double years;

    @Deprecated
    DoubleYears() {
    }

    public DoubleYears(final double value) {
        this.years = value;
    }

    @Override
    public DoubleYears plus(final Years that) {
        return new DoubleYears(years + that.doubleValue());
    }

    @Override
    public DoubleYears times(final Number that, final RoundingMode rounding) {
        return new DoubleYears(years * that.doubleValue());
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return BigDecimal.valueOf(years).round(context);
    }

    @Override
    public Period toPeriod(final double daysPerYear) {
        final int wholeYears = (int) years;
        final int wholeDays = (int) (daysPerYear * (years - wholeYears));
        return Period.of(wholeYears, 0, wholeDays);
    }

    @Override
    public Years reciprocal() {
        return new DoubleYears(1 / years);
    }

}
