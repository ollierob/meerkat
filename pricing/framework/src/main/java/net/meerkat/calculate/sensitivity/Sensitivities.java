package net.meerkat.calculate.sensitivity;

import java.util.Optional;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Sensitivities {

    @Nonnull
    <S extends Sensitivity<?>> Optional<S> get(Class<S> sensitivity);

}
