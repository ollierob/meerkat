package net.meerkat.money.interest.curve;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import net.meerkat.utils.SelfTyped;
import net.ollie.goat.collection.Maps;
import net.ollie.goat.collection.Sets;
import net.ollie.goat.numeric.interpolation.Interpolator;
import net.ollie.goat.numeric.manifold.Curve;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public abstract class MappedYieldCurve<K, T extends MappedYieldCurve<K, T>>
        implements YieldCurve<K>, SelfTyped<T> {

    private final NavigableMap<K, Percentage> map;

    protected MappedYieldCurve(final NavigableMap<K, Percentage> map) {
        this.map = Collections.unmodifiableNavigableMap(new TreeMap<>(map));
    }

    protected MappedYieldCurve(final Map<K, Percentage> map, final Comparator<? super K> comparator) {
        final TreeMap<K, Percentage> navigable = new TreeMap<>(comparator);
        navigable.putAll(map);
        this.map = Collections.unmodifiableNavigableMap(navigable);
    }

    @Override
    public Percentage at(final K x) {
        return map.get(x);
    }

    @Override
    public Percentage get(final K x, final Interpolator<K, Percentage> interpolator) {
        return interpolator.interpolate(x, map);
    }

    public Entry<K, Percentage> interpolate(final K key, final Interpolator<K, Percentage> interpolator) {
        return interpolator.interpolateEntry(key, map);
    }

    @Override
    public NavigableMap<K, Percentage> toMap() {
        return Collections.unmodifiableNavigableMap(map);
    }

    protected abstract T with(Map<K, Percentage> curve);

    @Override
    public T plus(final Percentage bump) {
        return this.with(Maps.lazilyTransformValues(map, p -> p.plus(bump)));
    }

    @Override
    public T plus(final Curve<K, Percentage> that, final Interpolator<K, Percentage> interpolator) {
        final Set<K> combinedAxis = Sets.eagerUnion(this.xAxis(), that.xAxis());
        final NavigableMap<K, Percentage> thatMap = that.toMap();
        final Map<K, Percentage> interpolatedCurve = Maps.lazilyGenerateValues(
                combinedAxis,
                x -> interpolator.interpolate(x, map).plus(interpolator.interpolate(x, thatMap)));
        return this.with(interpolatedCurve);
    }

}
