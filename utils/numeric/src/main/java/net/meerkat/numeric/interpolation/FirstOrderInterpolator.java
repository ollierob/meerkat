package net.meerkat.numeric.interpolation;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.Map;
import java.util.NavigableMap;

/**
 * @author ollie
 */
public interface FirstOrderInterpolator<K, V> extends Interpolator<K, V> {

    @Override
    @CheckForNull
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

    @CheckForNull
    default V defaultValue(final K key) {
        return null;
    }

    @Nonnull
    V extrapolateLeft(K key, K ceilingKey, V ceilingValue);

    @Nonnull
    V extrapolateRight(K key, K floorKey, V floorValue);

    @Nonnull
    V interpolate(K key, K floorKey, K ceilingKey, V floorValue, V ceilingValue);

}
