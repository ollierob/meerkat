package net.meerkat.calculate.sensitivity;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.Explainable;

/**
 *
 * @author Ollie
 * @see InstrumentSensitivities
 */
public interface Sensitivities extends Explainable {

    @Nonnull
    <S extends Sensitivity> Optional<S> get(SensitivityId<S> id);

}
