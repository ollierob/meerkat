package net.ollie.meerkat.calculate.sensitivity;

import java.time.temporal.Temporal;
import java.util.Optional;

import javax.annotation.Nonnull;

import net.ollie.meerkat.security.SecurityDefinition;

/**
 *
 * @author Ollie
 */
public interface SensitivityCalculator<T extends Temporal> {

    @Nonnull
    SensitivityMap allSensitivities(T temporal, SecurityDefinition security);

    @Nonnull
    <T> Optional<T> sensitivity(T temporal, SecurityDefinition security, Sensitivity<T> sensitivity);

}
