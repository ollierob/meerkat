package net.ollie.meerkat.utils.numeric.manifold;

import javax.annotation.CheckForNull;

import net.ollie.meerkat.utils.numeric.interpolation.Interpolator;

/**
 *
 * @author Ollie
 */
public interface Curve<X, Y> {

    @CheckForNull
    Y at(X x);

    @CheckForNull
    Y get(X x, Interpolator<X, Y> interpolator);

}
