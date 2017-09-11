package net.meerkat.calculate.sensitivity;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface HasSensitivityId {

    @Nonnull
    SensitivityId<?> sensitivityId();

}
