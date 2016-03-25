package net.ollie.meerkat.calculate.sensitivity;

import javax.annotation.Nonnull;

import net.ollie.meerkat.utils.Accumulator;
import net.ollie.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
public interface Sensitivity<T> extends HasName {

    @Nonnull
    Accumulator<?, T> accumulator();

}
