package net.ollie.meerkat.calculate.sensitivity;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.ollie.meerkat.security.SecurityDefinition;

/**
 *
 * @author Ollie
 */
public interface SensitivityCalculator {

    @Nonnull
    SensitivityMap allSensitivities(SecurityDefinition security);

    @Nonnull
    <T> Optional<T> sensitivity(SecurityDefinition security, Sensitivity<T> sensitivity);

}
