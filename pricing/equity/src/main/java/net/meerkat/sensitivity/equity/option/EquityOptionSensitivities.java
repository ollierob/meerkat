package net.meerkat.sensitivity.equity.option;

import java.util.Optional;

import net.meerkat.calculate.sensitivity.Sensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;
import net.meerkat.calculate.sensitivity.greeks.HasGreeks;
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
        return GREEKS.maybeGet(id)
                .map(func -> func.apply(this))
                .flatMap(id::convert);
    }

}
