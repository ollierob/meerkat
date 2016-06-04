package net.ollie.meerkat.numeric.interest.interpolation;

import java.math.BigDecimal;

import net.ollie.goat.numeric.fraction.DecimalFraction;
import net.ollie.goat.numeric.interpolation.Interpolator;

/**
 *
 * @author ollie
 */
public interface DecimalInterpolator<K, V> extends Interpolator<K, V> {

    BigDecimal numerical(K key);

    V multiply(V value, DecimalFraction multiplier);

    V add(V left, V right);

    default V subtract(final V subtrahend, final V minuend) {
        return this.add(minuend, this.negate(minuend));
    }

    default V negate(final V value) {
        return this.multiply(value, DecimalFraction.MINUS_ONE);
    }

}
