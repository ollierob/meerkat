package net.meerkat.numeric.percentage;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author ollie
 */
public class IntegerPercentage extends Percentage {

    private static final long serialVersionUID = 1L;
    public static final IntegerPercentage ZERO_PERCENT = new IntegerPercentage(0);
    public static final IntegerPercentage ONE_PERCENT = new IntegerPercentage(1);
    public static final IntegerPercentage ONE_HUNDRED_PERCENT = new IntegerPercentage(100);

    public static IntegerPercentage of(final long percent) {
        return percent == 0
                ? ZERO_PERCENT
                : new IntegerPercentage(percent);
    }

    private final long value;

    IntegerPercentage(final long value) {
        this.value = value;
    }

    @Override
    public Percentage reciprocal() {
        return FractionalPercentage.of(1, value);
    }

    @Override
    public int intValue() {
        return Math.toIntExact(this.longValue());
    }

    @Override
    public long longValue() {
        return value / 100;
    }

    @Override
    public float floatValue() {
        return (float) this.doubleValue();
    }

    @Override
    public double doubleValue() {
        return value / 100d;
    }

    @Override
    public Percentage plus(final Percentage that) {
        return that instanceof IntegerPercentage
                ? this.plus((IntegerPercentage) that)
                : super.plus(that);
    }

    public IntegerPercentage plus(final IntegerPercentage that) {
        return of(Math.addExact(value, that.value));
    }

    @Override
    public Percentage times(final Number that, final RoundingMode rounding) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IntegerPercentage times(final long that) {
        return of(Math.multiplyExact(value, that));
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return new BigDecimal(this.doubleValue(), context);
    }

    @Override
    public boolean isPositive() {
        return value > 0;
    }

    @Override
    public boolean isZero() {
        return value == 0;
    }

    @Override
    public boolean isNegative() {
        return value < 0;
    }

    @Override
    public String toString() {
        return value + "%";
    }

}
