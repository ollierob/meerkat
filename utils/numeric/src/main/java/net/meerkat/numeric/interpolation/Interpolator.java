package net.meerkat.numeric.interpolation;

import net.meerkat.functions.Functions;
import net.meerkat.numeric.timeseries.TemporalSeries;

import javax.annotation.CheckForNull;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map.Entry;
import java.util.NavigableMap;

/**
 * @author Ollie
 */
public interface Interpolator<K, V> {

    @CheckForNull
    V interpolate(K key, NavigableMap<K, V> map);

    @CheckForNull
    default Entry<K, V> interpolateEntry(final K key, final NavigableMap<K, V> map) {
        return Functions.ifNonNull(this.interpolate(key, map), value -> new SimpleImmutableEntry<>(key, value));
    }

    V interpolate(K key, TemporalSeries<K, V> series);

}
