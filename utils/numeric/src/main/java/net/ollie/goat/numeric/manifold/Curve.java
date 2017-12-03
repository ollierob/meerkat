package net.ollie.goat.numeric.manifold;

import net.ollie.goat.numeric.interpolation.Interpolator;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;

/**
 * @author Ollie
 */
public interface Curve<X, Y> {

    @CheckForNull
    Y at(X x);

    @CheckForNull
    Y get(X x, Interpolator<X, Y> interpolator);

    @Nonnull
    NavigableMap<X, Y> toMap();

    default Set<X> xAxis() {
        return this.toMap().keySet();
    }

    default Collection<Y> yAxis() {
        return this.toMap().values();
    }

    default Collection<Entry<X, Y>> points() {
        return this.toMap().entrySet();
    }

    Curve<X, Y> plus(Curve<X, Y> that, Interpolator<X, Y> interpolator);

}
