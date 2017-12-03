package net.meerkat.money.interest.curve;

import net.meerkat.collections.Maps;
import net.meerkat.collections.Sets;
import net.meerkat.numeric.interpolation.Interpolator;
import net.meerkat.numeric.manifold.Curve;
import net.meerkat.numeric.percentage.Percentage;
import net.meerkat.objects.SelfTyped;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.Map.Entry;

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

    public T times(@Nonnull final Number bump) {
        return this.with(Maps.lazilyTransformValues(map, p -> p.times(bump)));
    }

    @Override
    public T plus(@Nonnull final Percentage bump) {
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
