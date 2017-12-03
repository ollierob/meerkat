package net.meerkat.numeric;

import net.meerkat.numeric.fraction.BigDecimalFraction;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Fixed decimal number.
 *
 * @author ollie
 */
public class SmallDecimal extends Number implements Numeric.Summable<SmallDecimal> {

    private static final long serialVersionUID = 1L;

    private static final byte BYTE_ZERO = 0;
    private static final int MAX_DP = 16;

    public static SmallDecimal valueOf(final Number number) {
        if (number instanceof SmallDecimal) {
            return (SmallDecimal) number;
        }
        if (number instanceof BigDecimal) {
            return valueOf((BigDecimal) number);
        }
        return Numbers.isEffectiveIntegral(number)
                ? valueOf(number.longValue())
                : valueOf(BigDecimal.valueOf(number.doubleValue()));
    }

    public static SmallDecimal valueOf(final long value) {
        return new SmallDecimal(value, BYTE_ZERO);
    }

    public static SmallDecimal valueOf(final BigDecimal decimal) {
        return new SmallDecimal(decimal.doubleValue(), min(decimal.scale(), (byte) MAX_DP));
    }

    public static SmallDecimal integer(final double value) {
        return new SmallDecimal((int) value, (byte) 0);
    }

    private static byte min(final int s1, final byte s2) {
        return s1 < s2 ? (byte) s1 : s2;
    }

    private final double value;
    private final byte dp;

    SmallDecimal(final double value, final byte dp) {
        if (dp < 0 || dp > MAX_DP) {
            throw new IllegalArgumentException("Invalid number of decimal places!");
        }
        this.value = value;
        this.dp = dp;
    }

    @Override
    public int intValue() {
        return (int) value;
    }

    @Override
    public long longValue() {
        return (long) value;
    }

    @Override
    public float floatValue() {
        return (float) this.doubleValue();
    }

    @Override
    public double doubleValue() {
        return Math.round(value * Math.pow(10, dp)) / Math.pow(10, dp);
    }

    private BigDecimal bd;

    @Override
    public BigDecimal decimalValue() {
        return bd == null
                ? (bd = new BigDecimal(value).setScale(dp, RoundingMode.HALF_UP))
                : bd;
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return new BigDecimal(value, context);
    }

    @Override
    public SmallDecimal plus(final SmallDecimal that) {
        return that.isZero()
                ? this
                : new SmallDecimal(value + that.value, min(dp, that.dp));
    }

    @Override
    public SmallDecimal negate() {
        return new SmallDecimal(-value, dp);
    }

    @Override
    public SmallDecimal times(final Number that, final RoundingMode rounding) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SmallDecimal over(final double value) {
        return new SmallDecimal(this.value / value, dp);
    }

    @Override
    public SmallDecimal reciprocal() {
        return new SmallDecimal(1 / value, dp);
    }

    public BigDecimalFraction over(final Number denominator) {
        return BigDecimalFraction.of(this, denominator);
    }

    @Override
    public String toString() {
        return this.decimalValue().toPlainString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SmallDecimal
                && this.equals((SmallDecimal) obj);
    }

    public boolean equals(@Nonnull final SmallDecimal that) {
        return this.decimalValue().equals(that.decimalValue());
    }

    @Override
    public int hashCode() {
        return this.decimalValue().hashCode();
    }

}
