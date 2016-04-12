package net.ollie.meerkat.numeric.interest.interpolation;

import java.util.Map;
import java.util.NavigableMap;

import net.ollie.meerkat.utils.numeric.interpolation.Interpolator;

/**
 *
 * @author ollie
 */
public interface FirstOrderInterpolator<K, V> extends Interpolator<K, V> {

    @Override
    default V interpolate(final K key, final NavigableMap<K, V> map) {
        final Map.Entry<K, V> floorEntry = map.floorEntry(key);
        final Map.Entry<K, V> ceilingEntry = map.ceilingEntry(key);
        if (floorEntry == null && ceilingEntry == null) {
            return this.defaultValue(key);
        } else if (floorEntry == null) {
            return this.extrapolateLeft(key, ceilingEntry.getKey(), ceilingEntry.getValue());
        } else if (ceilingEntry == null) {
            return this.extrapolateRight(key, floorEntry.getKey(), floorEntry.getValue());
        } else {
            return this.interpolate(key, floorEntry.getKey(), ceilingEntry.getKey(), floorEntry.getValue(), ceilingEntry.getValue());
        }
    }

    default V defaultValue(final K key) {
        return null;
    }

    V extrapolateLeft(K key, K ceilingKey, V ceilingValue);

    V extrapolateRight(K key, K floorKey, V floorValue);

    V interpolate(K key, K floorKey, K ceilingKey, V floorValue, V ceilingValue);

}
