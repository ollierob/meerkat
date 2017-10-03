package net.meerkat.sensitivity.equity.option;

import java.util.Optional;
import java.util.function.Function;

import net.coljate.map.ImmutableMap;
import net.coljate.map.MutableMap;
import net.meerkat.calculate.sensitivity.Sensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;
import net.meerkat.calculate.sensitivity.greeks.Delta;
import net.meerkat.calculate.sensitivity.greeks.Gamma;
import net.meerkat.calculate.sensitivity.greeks.HasGreeks;
import net.meerkat.calculate.sensitivity.greeks.Rho;
import net.meerkat.calculate.sensitivity.greeks.Theta;
import net.meerkat.calculate.sensitivity.greeks.Vega;
import net.meerkat.pricing.option.EvaluatedOptionPrice;
import net.meerkat.calculate.sensitivity.PriceSensitivities;

/**
 *
 * @author ollie
 */
public interface EquityOptionSensitivities extends PriceSensitivities, HasGreeks {

    @Override
    EvaluatedOptionPrice<?> price();

    @Override
    default <S extends Sensitivity> Optional<S> get(final SensitivityId<S> id) {
        return MAPPINGS.maybeGet(id)
                .map(func -> func.apply(this))
                .flatMap(id::convert);
    }

    ImmutableMap<SensitivityId<?>, Function<EquityOptionSensitivities, Sensitivity>> MAPPINGS = MutableMap.<SensitivityId<?>, Function<EquityOptionSensitivities, Sensitivity>>createHashMap()
            .union(Delta.ID, EquityOptionSensitivities::delta)
            .union(Gamma.ID, EquityOptionSensitivities::gamma)
            .union(Theta.ID, EquityOptionSensitivities::theta)
            .union(Rho.ID, EquityOptionSensitivities::rho)
            .union(Vega.ID, EquityOptionSensitivities::vega)
            .immutableCopy();

}
