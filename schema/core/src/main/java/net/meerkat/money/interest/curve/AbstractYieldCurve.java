package net.meerkat.money.interest.curve;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;

import net.ollie.goat.collection.Maps;
import net.ollie.goat.numeric.interpolation.Interpolator;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
@XmlSeeAlso({DateYieldCurve.class, TenorYieldCurve.class, YearsYieldCurve.class})
public abstract class AbstractYieldCurve<K> implements YieldCurve<K> {

    @XmlElementWrapper
    private NavigableMap<K, Percentage> map;

    @Deprecated
    protected AbstractYieldCurve() {
    }

    protected AbstractYieldCurve(final NavigableMap<K, Percentage> map) {
        this.map = Collections.unmodifiableNavigableMap(new TreeMap<>(map));
    }

    protected AbstractYieldCurve(final Map<K, Percentage> map, final Comparator<? super K> comparator) {
        final TreeMap<K, Percentage> navigable = new TreeMap<>(comparator);
        navigable.putAll(map);
        this.map = Collections.unmodifiableNavigableMap(navigable);
    }

    @Override
    public Percentage at(final K x) {
        return map.get(x);
    }

    @Override
    public Percentage get(final K x, Interpolator<K, Percentage> interpolator) {
        return interpolator.interpolate(x, map);
    }

    public Entry<K, Percentage> interpolate(final K key, final Interpolator<K, Percentage> interpolator) {
        return interpolator.interpolateEntry(key, map);
    }

    @Override
    public NavigableMap<K, Percentage> toMap() {
        return Collections.unmodifiableNavigableMap(map);
    }

    @Override
    public YieldCurve<K> plus(final Percentage bump) {
        return this.copy(Maps.lazilyTransformValues(map, p -> p.plus(bump)));
    }

    protected abstract AbstractYieldCurve<K> copy(Map<K, Percentage> curve);

}
