package net.meerkat.numeric.decimal;

import net.meerkat.numeric.Numeric;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Ollie
 */
public abstract class BigDecimals {

    public static final BigDecimal ONE = BigDecimal.ONE;
    public static final BigDecimal TWO = BigDecimal.valueOf(2);
    public static final BigDecimal ONE_HUNDRED = BigDecimal.ONE.movePointRight(2);

    private static final Map<Class<?>, Function<Number, BigDecimal>> decimalConversions;

    static {
        final Map<Class<?>, Function<Number, BigDecimal>> funcs = new HashMap<>();
        funcs.put(BigDecimal.class, n -> (BigDecimal) n);
        funcs.put(BigInteger.class, n -> new BigDecimal((BigInteger) n));
        funcs.put(Integer.class, n -> toBigDecimal(n.intValue()));
        funcs.put(Short.class, n -> BigDecimal.valueOf(n.intValue()));
        funcs.put(Long.class, n -> BigDecimal.valueOf(n.longValue()));
        funcs.put(Double.class, n -> BigDecimal.valueOf(n.doubleValue()));
        funcs.put(Float.class, n -> BigDecimal.valueOf(n.floatValue()));
        decimalConversions = Collections.unmodifiableMap(funcs);
    }

    private static final BigDecimal[] precomputed;

    static {
        precomputed = new BigDecimal[20];
        precomputed[0] = BigDecimal.ZERO;
        precomputed[1] = ONE;
        precomputed[2] = TWO;
        for (int i = 3; i < precomputed.length; i++) {
            precomputed[i] = BigDecimal.valueOf(i);
        }
    }

    protected BigDecimals() {
        throw new AbstractMethodError();
    }

    @Nonnull
    public static BigDecimal toBigDecimal(@Nonnull final Number number) {
        return decimalConversions.getOrDefault(number.getClass(), BigDecimals::genericConversion).apply(number);
    }

    private static BigDecimal genericConversion(final Number number) {
        return number instanceof Numeric n
                ? n.decimalValue()
                : BigDecimal.valueOf(number.doubleValue());
    }

    @Nonnull
    public static boolean isOne(@Nonnull final BigDecimal that) {
        return that == BigDecimal.ONE || that.compareTo(BigDecimal.ONE) == 0;
    }

    public static boolean valuesEqual(@Nullable final BigDecimal b1, @Nullable final BigDecimal b2) {
        return b1 == b2
                || (b1 != null && b2 != null && b1.compareTo(b2) == 0);
    }

    public static BigDecimal toBigDecimal(final long i) {
        return i >= 0 && i < precomputed.length
                ? precomputed[(int) i]
                : BigDecimal.valueOf(i);
    }

    public static BigDecimal mean(final BigDecimal d1, final BigDecimal d2, final MathContext context) {
        return d1.add(d2).divide(TWO, context);
    }

}
