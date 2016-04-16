package net.ollie.meerkat.utils.time;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Period;

import net.ollie.meerkat.utils.numeric.Numbers;
import net.ollie.meerkat.utils.numeric.Numeric;

/**
 *
 * @author Ollie
 */
public class Days implements Numeric.Summable<Days> {

    private static final long serialVersionUID = 1L;

    private final int numDays;

    public Days(final int numDays) {
        this.numDays = numDays;
    }

    public int value() {
        return numDays;
    }

    public Period toPeriod() {
        return Period.ofDays(numDays);
    }

    @Override
    public Days negate() {
        return new Days(-numDays);
    }

    @Override
    public Days times(final Number that, final RoundingMode rounding) {
        return new Days(Numbers.round(numDays * that.doubleValue(), rounding));
    }

    public Days over(final Number that, final RoundingMode rounding) {
        return new Days(Numbers.round(numDays / that.doubleValue(), rounding));
    }

    @Override
    public Days plus(final Days that) {
        return new Days(numDays + that.numDays);
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return BigDecimal.valueOf(numDays);
    }

    @Override
    public String toString() {
        return numDays + " days";
    }

}
