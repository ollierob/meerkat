package net.ollie.meerkat.calculate.sensitivity;

import javax.annotation.Nonnull;

import net.meerkat.utils.Accumulator;
import net.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
public interface Sensitivity<T> extends HasName {

    @Nonnull
    Accumulator<?, T> accumulator();

    @Override
    default String name() {
        return this.getClass().getSimpleName();
    }

}
