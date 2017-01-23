package net.meerkat.instrument.derivative.swap;

import javax.annotation.Nonnull;

import net.meerkat.instrument.Security;
import net.ollie.meerkat.utils.collections.sequence.Sequence;

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
