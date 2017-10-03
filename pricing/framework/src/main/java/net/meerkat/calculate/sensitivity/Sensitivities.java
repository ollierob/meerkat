package net.meerkat.calculate.sensitivity;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.Explainable;
import net.meerkat.money.price.Price;

/**
 *
 * @author Ollie
 * @see InstrumentSensitivities
 */
public interface Sensitivities extends Explainable {

    @Nonnull
    Price.Evaluated<?> price();

    @Nonnull
    <S extends Sensitivity> Optional<S> get(SensitivityId<S> id);

}
