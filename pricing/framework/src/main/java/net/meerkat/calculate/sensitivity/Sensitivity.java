package net.meerkat.calculate.sensitivity;

import javax.annotation.Nonnull;

import net.meerkat.utils.HasName;
import net.meerkat.utils.SelfTyped;

/**
 *
 * @author Ollie
 */
public interface Sensitivity<S extends Sensitivity<S>> extends SelfTyped<S>, HasName {

    @Nonnull
    S plus(@Nonnull S that);

    @Override
    default String name() {
        return this.getClass().getSimpleName();
    }

}
