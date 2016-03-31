package net.ollie.meerkat.utils.numeric;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.annotation.Nonnull;

import net.ollie.meerkat.utils.SelfTyped;
import static net.ollie.meerkat.utils.numeric.Numbers.toBigDecimal;

/**
 *
 * @author Ollie
 */
public interface Numeric<T extends Numeric<T>> extends Comparable<T>, SelfTyped<T> {

    default boolean isZero() {
        return this.decimalValue().signum() == 0;
    }

    default T negate() {
        return this.times(-1, RoundingMode.UNNECESSARY);
    }

    @Nonnull
    T times(@Nonnull Number that, RoundingMode rounding);

    @Nonnull
    default T over(@Nonnull final Number that, @Nonnull final MathContext context) {
        return this.times(BigDecimal.ONE.divide(toBigDecimal(that), context), context.getRoundingMode());
    }

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

    interface Summable<T extends Summable<T>> extends Numeric<T> {

        T plus(@Nonnull T that);

        default T minus(@Nonnull final T that) {
            return this.plus(that.negate());
        }

    }

}
