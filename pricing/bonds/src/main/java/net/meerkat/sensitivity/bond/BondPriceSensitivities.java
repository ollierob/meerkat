package net.meerkat.sensitivity.bond;

import java.util.Optional;
import java.util.function.Function;

import javax.annotation.Nonnull;

import net.coljate.map.ImmutableMap;
import net.meerkat.calculate.sensitivity.Sensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;
import net.meerkat.calculate.sensitivity.yield.DollarDuration;
import net.meerkat.pricing.bond.BondPrice;
import net.meerkat.pricing.bond.EvaluatedBondPrice;
import net.meerkat.calculate.sensitivity.PriceSensitivities;

/**
 *
 * @author Ollie
 * @see BondPrice
 */
public interface BondPriceSensitivities extends PriceSensitivities {

    ImmutableMap<SensitivityId<?>, Function<BondPriceSensitivities, Sensitivity>> SENSITIVITY_MAP = ImmutableMap.of(DollarDuration.ID, BondPriceSensitivities::dollarDuration);

    @Override
    EvaluatedBondPrice<?> price();

    @Nonnull
    DollarDuration dollarDuration();

    @Override
    default <S extends Sensitivity> Optional<S> get(final SensitivityId<S> id) {
        return SENSITIVITY_MAP.maybeGet(id)
                .map(func -> func.apply(this))
                .flatMap(id::convert);
    }

}
