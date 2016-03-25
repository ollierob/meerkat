package net.ollie.meerkat.security.derivative.swap;

import javax.annotation.Nonnull;

import net.ollie.meerkat.security.Security;
import net.ollie.meerkat.utils.collections.Sequence;

/**
 *
 * @author Ollie
 */
public interface Swap extends Security {

    @Nonnull
    Sequence<? extends SwapLeg> legs();

    default boolean isBullet() {
        return this.legs().count().map(i -> i == 1).orElse(false);
    }

}
