package net.meerkat.numeric.manifold;

import net.meerkat.numeric.interpolation.Interpolator;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Set;

public class MappedCurve<X, Y> implements Curve<X, Y> {

    private final NavigableMap<X, Y> map;

    public MappedCurve(final NavigableMap<X, Y> map) {
        this.map = Objects.requireNonNull(map);
    }

    @CheckForNull
    @Override
    public Y at(final X x) {
        return map.get(x);
    }

    @CheckForNull
    @Override
    public Y get(final X x, final Interpolator<X, Y> interpolator) {
        return interpolator.interpolate(x, map);
    }

    @Nonnull
    @Override
    public NavigableMap<X, Y> toMap() {
        return Collections.unmodifiableNavigableMap(map);
    }

    @Override
    public Set<X> xAxis() {
        return Collections.unmodifiableSet(map.keySet());
    }

}
