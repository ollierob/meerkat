package net.meerkat.calculate.sensitivity;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface SensitivityMap {

    @Nonnull
    <T> Optional<T> get(Sensitivity<T> sensitivity);

    @Nonnull
    Map<Sensitivity<?>, Object> toMap();

}
