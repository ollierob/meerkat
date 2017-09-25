package net.meerkat.calculate.sensitivity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.coljate.util.SelfTyped;
import net.meerkat.utils.Classes.Castable;

/**
 *
 * @author Ollie
 */
public interface Sensitivity extends HasSensitivityId, Castable {

    interface Summing<S extends Summing<S>> extends Sensitivity, SelfTyped<S> {

        @Nonnull
        S plus(@Nullable S that);

    }

}
