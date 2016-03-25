package net.ollie.meerkat.utils.numeric;

import java.math.BigDecimal;

import net.ollie.goat.functions.Functions;

/**
 *
 * @author Ollie
 */
public class Numbers {

    protected Numbers() {
    }

    public static boolean isNativeIntegral(final Number number) {
        return number instanceof Integer || number instanceof Long;
    }

    public static BigDecimal toBigDecimal(final Number number) {
        if (isNativeIntegral(number)) {
            switch (number.intValue()) {
                case 0:
                    return BigDecimal.ZERO;
                case 1:
                    return BigDecimal.ONE;
                case 10:
                    return BigDecimal.TEN;
                default:
                    return BigDecimal.valueOf(number.longValue());
            }
        }
        return number instanceof BigDecimal
                ? (BigDecimal) number
                : new BigDecimal(number.toString());
    }

    public static boolean isOne(final Number number) {
        if (isNativeIntegral(number)) {
            return number.intValue() == 1;
        }
        return isOne(toBigDecimal(number));
    }

    public static boolean isOne(final BigDecimal number) {
        return number == BigDecimal.ONE || number.compareTo(BigDecimal.ONE) == 0;
    }

    public static boolean equals(final Number left, final Number right) {
        return left == null
                ? right == null
                : right != null && left.doubleValue() == right.doubleValue(); //FIXME
    }

    public static Integer add(final Integer left, final Integer right) {
        return Functions.ifBothNonNull(left, right, (final Integer i, final Integer j) -> i + j);
    }

}
