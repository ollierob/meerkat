package net.meerkat.security.derivative;

import net.meerkat.security.Security;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface Derivative<S extends Security> extends Security {

    @Nonnull
    S underlying();

    @Nonnull
    default Security ultimateUnderlying() {
        Security self = this;
        while (true) {
            Security underlying = self instanceof Derivative
                    ? ((Derivative) self).underlying()
                    : null;
            if (underlying == null || underlying == self) {
                return self;
            }
            self = underlying;
        }
    }

}
