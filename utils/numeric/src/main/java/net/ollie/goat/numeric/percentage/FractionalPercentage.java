package net.ollie.goat.numeric.percentage;

import net.ollie.goat.numeric.fraction.BigDecimalFraction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author ollie
 */
public class FractionalPercentage extends Percentage {

    private static final long serialVersionUID = 1L;

    public static Percentage of(final long numerator, final long denominator) {
        final BigDecimalFraction fraction = BigDecimalFraction.of(numerator, denominator);
        return fraction.isZero()
                ? zero()
                : new FractionalPercentage(fraction);
    }

    public static Percentage of(final Number numerator, final Number denominator) {
        final BigDecimalFraction fraction = BigDecimalFraction.of(numerator, denominator);
        return fraction.isZero()
                ? Percentage.zero()
                : new FractionalPercentage(fraction);
    }

    private final BigDecimalFraction fraction;

    FractionalPercentage(final BigDecimalFraction fraction) {
        this.fraction = fraction;
    }

    @Override
    public boolean isNegative() {
        return fraction.isNegative();
    }

    @Override
    public FractionalPercentage reciprocal() {
        return new FractionalPercentage(fraction.reciprocal());
    }

    @Override
    public int intValue() {
        return fraction.intValue();
    }

    @Override
    public long longValue() {
        return fraction.longValue();
    }

    @Override
    public float floatValue() {
        return fraction.floatValue();
    }

    @Override
    public double doubleValue() {
        return fraction.doubleValue();
    }

    @Override
    public Percentage times(Number that, RoundingMode rounding) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BigDecimal decimalValue() {
        return fraction.decimalValue();
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return fraction.decimalValue(context);
    }

}
