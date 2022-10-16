package net.meerkat.numeric.interpolation;

import net.meerkat.numeric.timeseries.TemporalSeries;

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

    @Override
    default V interpolate(final K k1, final TemporalSeries<K, V> series) {
        final var k0 = series.prev(k1);
        final var k2 = series.next(k1);
        return this.interpolate(k1, k0, k2, series.at(k0), series.at(k2));
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
