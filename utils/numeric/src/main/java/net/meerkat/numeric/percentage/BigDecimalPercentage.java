package net.meerkat.numeric.percentage;

import net.meerkat.numeric.BigDecimals;
import net.meerkat.numeric.Numeric;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * @author Ollie
 */
public class BigDecimalPercentage extends Percentage {

    private static final long serialVersionUID = 1L;
    public static final Percentage ZERO_PERCENT = new BigDecimalPercentage(BigDecimal.ZERO);
    public static final Percentage ONE_BP = new BigDecimalPercentage(BigDecimal.ONE.movePointLeft(4));
    public static final Percentage ONE_PERCENT = new BigDecimalPercentage(BigDecimal.ONE.movePointLeft(2));
    public static final Percentage ONE_HUNDRED_PERCENT = new BigDecimalPercentage(BigDecimal.ONE);

    public static Percentage basisPoints(final int amount) {
        switch (amount) {
            case 0:
                return ZERO_PERCENT;
            case 1:
                return ONE_BP;
            default:
                return new BigDecimalPercentage(BigDecimal.valueOf(amount).movePointLeft(4));
        }
    }

    private final BigDecimal value;

    public BigDecimalPercentage(final int value) {
        this(BigDecimal.valueOf(value).movePointLeft(2));
    }

    public BigDecimalPercentage(final double value) {
        this(BigDecimal.valueOf(value / 100));
    }

    BigDecimalPercentage(final BigDecimal value) {
        this.value = value;
    }

    @Override
    public Percentage times(final Number that, final RoundingMode rounding) {
        return this.times(that);
    }

    @Override
    public Percentage times(final Number that) {
        return new BigDecimalPercentage(value.multiply(BigDecimals.toBigDecimal(that)));
    }

    public Percentage timesBy(final Numeric<?> that) {
        return this.times(that.decimalValue());
    }

    @Override
    public BigDecimal decimalValue() {
        return value;
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return this.decimalValue().round(context);
    }

    @Override
    public double doubleValue() {
        return this.decimalValue().doubleValue();
    }

    @Override
    public int intValue() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public long longValue() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public float floatValue() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public boolean isNegative() {
        return value.signum() < 0;
    }

    @Override
    public Percentage reciprocal() {
        return FractionalPercentage.of(1, value);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Percentage
                && this.valuesEqual((Percentage) obj);
    }

}
