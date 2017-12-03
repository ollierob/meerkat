package net.meerkat.temporal.date.years;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

/**
 * @author Ollie
 */
public class IntegerYears implements Years {

    private static final long serialVersionUID = 1L;

    static final IntegerYears ZERO = new IntegerYears(0);
    static final IntegerYears ONE = new IntegerYears(1);

    public static IntegerYears of(final int years) {
        switch (years) {
            case 0:
                return ZERO;
            case 1:
                return ONE;
            default:
                return new IntegerYears(years);
        }
    }

    private final int years;

    protected IntegerYears(final int years) {
        this.years = years;
    }

    @Override
    public LocalDate addTo(final LocalDate date) {
        return date.plusYears(years);
    }

    @Override
    public Years plus(final Years that) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public Years times(final Number that, final RoundingMode rounding) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return BigDecimal.valueOf(years);
    }

    @Override
    public Period period() {
        return Period.ofYears(years);
    }

    @Override
    public Period toPeriod(final double daysPerYear) {
        return this.period();
    }

    @Override
    public Years reciprocal() {
        return FractionalYears.of(1, years);
    }

}
