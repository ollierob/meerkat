package net.meerkat.numeric;

import net.meerkat.numeric.decimal.BigDecimalFraction;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author Ollie
 */
public interface Numeric<T extends Numeric<T>>
        extends Comparable<T>, Serializable {

    default Number value() {
        return this.decimalValue();
    }

    default boolean isZero() {
        return this.decimalValue().signum() == 0;
    }

    default boolean isPositive() {
        return this.decimalValue().signum() > 0;
    }

    default boolean isNegative() {
        return this.decimalValue().signum() < 0;
    }

    default T negate() {
        return this.times(-1, RoundingMode.UNNECESSARY);
    }

    @Nonnull
    T times(@Nonnull Number that, RoundingMode rounding);

    default T times(final long that) {
        return this.times(that, RoundingMode.UNNECESSARY);
    }

    @Nonnull
    default T times(@Nonnull final Number that) {
        return this.times(that, RoundingMode.HALF_UP);
    }

    @Nonnull
    default T over(@Nonnull final Number that, @Nonnull final MathContext context) {
        return this.times(BigDecimalFraction.of(1, that), context.getRoundingMode());
    }

    @Nonnull
    T reciprocal();

    /**
     * @return the decimal value of this amount, if possible to exact precision, or
     * {@link MathContext#DECIMAL128 quad precision} if not.
     */
    @Nonnull
    default BigDecimal decimalValue() {
        return this.decimalValue(MathContext.DECIMAL128);
    }

    BigDecimal decimalValue(MathContext context);

    default double doubleValue() {
        return this.decimalValue(MathContext.DECIMAL64).doubleValue();
    }

    @Override
    default int compareTo(final T that) {
        return this.decimalValue().compareTo(that.decimalValue());
    }

    default boolean valuesEqual(@Nonnull final T that) {
        return this.decimalValue().compareTo(that.decimalValue()) == 0;
    }

    default boolean valuesEqual(@Nonnull final T that, final double delta) {
        return Math.abs(this.decimalValue().subtract(that.decimalValue()).doubleValue()) < delta;
    }

    interface Summable<T extends Summable<T>> extends Numeric<T> {

        T plus(@Nonnull T that);

        default T minus(@Nonnull final T that) {
            return this.plus(that.negate());
        }

    }

}
