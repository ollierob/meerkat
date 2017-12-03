package net.ollie.goat.numeric.interpolation;

import net.ollie.goat.numeric.fraction.BigDecimalFraction;

import java.math.BigDecimal;

/**
 * @author ollie
 */
public interface DecimalInterpolator<K, V> extends Interpolator<K, V> {

    BigDecimal numerical(K key);

    V multiply(V value, BigDecimalFraction multiplier);

    V add(V left, V right);

    default V subtract(final V subtrahend, final V minuend) {
        return this.add(minuend, this.negate(minuend));
    }

    default V negate(final V value) {
        return this.multiply(value, BigDecimalFraction.MINUS_ONE);
    }

}
