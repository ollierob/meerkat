package net.ollie.goat.temporal.date.years;

import net.ollie.goat.temporal.date.Periods;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Period;

/**
 * @author Ollie
 */
public class PeriodYears implements Years {

    private static final long serialVersionUID = 1L;

    private final Period period;

    public PeriodYears(final Period period) {
        this.period = period;
    }

    @Override
    public Period period() {
        return period;
    }

    @Override
    public Period toPeriod(final double daysPerYear) {
        return period;
    }

    @Override
    public double doubleValue() {
        return Periods.approximateLength(period);
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return BigDecimal.valueOf(this.doubleValue()).round(context);
    }

    @Override
    public Years reciprocal() {
        return new DoubleYears(1 / doubleValue());
    }

}
