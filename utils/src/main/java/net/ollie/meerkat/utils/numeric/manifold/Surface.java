package net.ollie.meerkat.utils.numeric.manifold;

import javax.annotation.CheckForNull;

/**
 *
 * @author Ollie
 */
public interface Surface<X, Y, Z> {

    @CheckForNull
    Z at(X x, Y y);

}
