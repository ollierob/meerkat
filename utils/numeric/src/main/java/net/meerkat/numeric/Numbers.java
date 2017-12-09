package net.meerkat.numeric;

import net.meerkat.functions.Functions;
import net.meerkat.numeric.decimal.BigDecimals;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Extension methods for numbers.
 *
 * @author Ollie
 */
public abstract class Numbers {

    private static final double DOUBLE_PRECISION = 1e-16;

    protected Numbers() {
    }

    public static boolean isNativeIntegral(@Nullable final Number number) {
        return number instanceof Integer || number instanceof Long;
    }

    public static boolean isEffectiveIntegral(final Number number) {
        return number != null
                && (isNativeIntegral(number) || isIntegral(number.doubleValue()));
    }

    public static boolean isIntegral(final double d) {
        return d == Math.floor(d);
    }

    public static boolean isOne(@Nullable final Number number) {
        if (number == null) {
            return false;
        }
        if (isNativeIntegral(number)) {
            return number.intValue() == 1;
        }
        return BigDecimals.isOne(BigDecimals.toBigDecimal(number));
    }

    public static boolean equals(@Nullable final Number left, @Nullable final Number right) {
        return left == null
                ? right == null
                : right != null && Math.abs(left.doubleValue() - right.doubleValue()) < DOUBLE_PRECISION; //FIXME
    }

    public static Integer add(final Integer left, final Integer right) {
        return Functions.ifBothNonNull(left, right, (final Integer i, final Integer j) -> i + j);
    }

    public static int round(final double d, final RoundingMode rounding) {
        switch (rounding) {
            case CEILING:
                return (int) Math.ceil(d);
            case FLOOR:
                return (int) Math.floor(d);
            default:
                return BigDecimal.valueOf(d).setScale(0, rounding).intValue();
        }
    }

    @SuppressWarnings("unchecked")
    public static int compare(final Number n1, final Number n2) {
        if (n1 instanceof Comparable && n1.getClass().isAssignableFrom(n2.getClass())) {
            return ((Comparable) n1).compareTo(n2);
        }
        return BigDecimals.toBigDecimal(n1).compareTo(BigDecimals.toBigDecimal(n2));
    }

}
