package net.meerkat.sensitivity.bond;

import java.util.Optional;
import java.util.function.Function;

import javax.annotation.Nonnull;

import net.coljate.map.ImmutableMap;
import net.meerkat.calculate.sensitivity.Sensitivities;
import net.meerkat.calculate.sensitivity.Sensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;
import net.meerkat.calculate.sensitivity.yield.DollarDuration;

/**
 *
 * @author Ollie
 */
public interface BondSensitivities extends Sensitivities {

    ImmutableMap<SensitivityId<?>, Function<BondSensitivities, Sensitivity>> SENSITIVITY_MAP = ImmutableMap.of(
            DollarDuration.ID, BondSensitivities::dollarDuration);

    @Nonnull
    DollarDuration dollarDuration();

    @Override
    default <S extends Sensitivity> Optional<S> get(final SensitivityId<S> id) {
        return SENSITIVITY_MAP.maybeGet(id)
                .map(func -> func.apply(this))
                .flatMap(id::convert);
    }

}
