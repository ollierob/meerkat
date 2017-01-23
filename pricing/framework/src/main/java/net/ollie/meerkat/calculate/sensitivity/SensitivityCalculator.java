package net.ollie.meerkat.calculate.sensitivity;

import java.time.temporal.Temporal;
import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.security.UnsupportedSecurityException;
import net.meerkat.instrument.InstrumentDefinition;

/**
 *
 * @author Ollie
 */
public interface SensitivityCalculator<T extends Temporal> {

    @Nonnull
    SensitivityMap allSensitivities(T temporal, InstrumentDefinition security) throws UnsupportedSecurityException;

    @Nonnull
    <T> Optional<T> sensitivity(T temporal, InstrumentDefinition security, Sensitivity<T> sensitivity);

}
