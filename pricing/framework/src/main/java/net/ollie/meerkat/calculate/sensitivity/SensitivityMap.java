package net.ollie.meerkat.calculate.sensitivity;

import javax.annotation.CheckForNull;

/**
 *
 * @author Ollie
 */
public interface SensitivityMap {

    @CheckForNull
    <T> T get(Sensitivity<T> sensitivity);

}
