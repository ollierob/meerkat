package net.meerkat.sensitivity.equity.option;

import java.util.Optional;
import java.util.function.Function;

import javax.annotation.Nonnull;

import net.coljate.map.ImmutableMap;
import net.coljate.map.MutableMap;
import net.meerkat.calculate.sensitivity.Sensitivities;
import net.meerkat.calculate.sensitivity.Sensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;
import net.meerkat.calculate.sensitivity.greeks.Delta;
import net.meerkat.calculate.sensitivity.greeks.Gamma;
import net.meerkat.calculate.sensitivity.greeks.Theta;

/**
 *
 * @author ollie
 */
public interface EquityOptionSensitivities extends Sensitivities {

    @Nonnull
    Delta delta();

    @Nonnull
    Gamma gamma();

    @Nonnull
    Theta theta();

    @Override
    default <S extends Sensitivity> Optional<S> get(final SensitivityId<S> id) {
        return MAPPINGS.maybeGet(id)
                .map(func -> func.apply(this))
                .flatMap(id::convert);
    }

    @Override
    public default java.util.Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("delta", this.delta())
                .put("gamma", this.gamma())
                .put("theta", this.theta());
    }

    ImmutableMap<SensitivityId<?>, Function<EquityOptionSensitivities, Sensitivity>> MAPPINGS = MutableMap.<SensitivityId<?>, Function<EquityOptionSensitivities, Sensitivity>>createHashMap()
            .union(Delta.ID, EquityOptionSensitivities::delta)
            .union(Gamma.ID, EquityOptionSensitivities::gamma)
            .union(Theta.ID, EquityOptionSensitivities::theta)
            .immutableCopy();

}
