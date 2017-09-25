package net.meerkat.calculate.sensitivity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.coljate.util.SelfTyped;
import net.meerkat.utils.Classes.Castable;
import net.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
public interface Sensitivity extends HasSensitivityId, HasName, Castable {

    @Override
    default String name() {
        return this.getClass().getSimpleName();
    }

    interface Summing<S extends Summing<S>> extends Sensitivity, SelfTyped<S> {

        @Nonnull
        S plus(@Nullable S that);

    }

}
