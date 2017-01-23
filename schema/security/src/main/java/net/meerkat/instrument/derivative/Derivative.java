package net.meerkat.instrument.derivative;


import javax.annotation.Nonnull;

import net.meerkat.security.Instrument;

/**
 *
 * @author ollie
 */
public interface Derivative<S extends Instrument> extends Instrument {

    @Nonnull
    S underlying();

    @Nonnull
    default Instrument ultimateUnderlying() {
        Instrument self = this;
        while (true) {
            Instrument underlying = self instanceof Derivative
                    ? ((Derivative) self).underlying()
                    : null;
            if (underlying == null || underlying == self) {
                return self;
            }
            self = underlying;
        }
    }

}
