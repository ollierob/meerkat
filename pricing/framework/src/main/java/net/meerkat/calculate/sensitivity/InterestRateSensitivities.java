package net.meerkat.calculate.sensitivity;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.calculate.sensitivity.yield.DollarDuration;

/**
 *
 * @author ollie
 */
public interface InterestRateSensitivities extends PriceSensitivities {

    @Nonnull
    DollarDuration dollarDuration();

    @Override
    default <S extends Sensitivity> Optional<S> get(final SensitivityId<S> id) {
        return INTEREST_RATE_SENSITIVITIES.get(this, id);
    }

    PriceSensitivitiesMapper<InterestRateSensitivities> INTEREST_RATE_SENSITIVITIES = PriceSensitivitiesMapper.<InterestRateSensitivities>builder()
            .put(DollarDuration.ID, InterestRateSensitivities::dollarDuration)
            .build();

}
