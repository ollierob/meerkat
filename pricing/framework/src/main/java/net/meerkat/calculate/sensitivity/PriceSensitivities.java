package net.meerkat.calculate.sensitivity;

import net.meerkat.Explainable;
import net.meerkat.money.price.Price;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 *
 * @author Ollie
 * @see UnitPriceSensitivities
 */
public interface PriceSensitivities extends Explainable {

    @Nonnull
    Price.Evaluated<?> price();

    @Nonnull
    <S extends Sensitivity> Optional<S> get(SensitivityId<S> id);

}
