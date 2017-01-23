package net.meerkat.instrument.derivative.swap;

import javax.annotation.Nonnull;

import net.ollie.meerkat.utils.collections.sequence.Sequence;
import net.meerkat.security.Instrument;

/**
 *
 * @author Ollie
 */
public interface Swap extends Instrument {

    @Nonnull
    Sequence<? extends SwapLeg> legs();

    default boolean isBullet() {
        return this.legs().count().map(i -> i == 1).orElse(false);
    }

}
