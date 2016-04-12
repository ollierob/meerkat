package net.ollie.meerkat.numeric.interest.interpolation;

import java.math.BigDecimal;

import net.ollie.meerkat.numeric.DecimalFraction;

/**
 *
 * @author ollie
 */
public abstract class LinearInterpolator<K, V>
        implements FirstOrderInterpolator<K, V>, DecimalInterpolator<K, V> {

    @Override
    public V interpolate(final K k, final K k0, final K k1, final V y0, final V y1) {
        final BigDecimal x = this.numerical(k);
        final BigDecimal x0 = this.numerical(k0);
        final BigDecimal x1 = this.numerical(k1);
        final V right = this.multiply(this.subtract(y1, y0), DecimalFraction.of(x.subtract(x0), x1.subtract(x0)));
        return this.add(y0, right);
    }

    @Override
    public V extrapolateLeft(final K key, final K ceilingKey, final V ceilingValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public V extrapolateRight(final K key, final K floorKey, final V floorValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
