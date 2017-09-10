package net.meerkat.calculate.sensitivity;

import net.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
public interface Sensitivity<T> extends HasName {

    @Override
    default String name() {
        return this.getClass().getSimpleName();
    }

}
