package net.meerkat.calculate.sensitivity;

import java.util.Optional;

import net.coljate.map.Map;

/**
 *
 * @author ollie
 */
public abstract class MappedSensitivities implements Sensitivities {

    private final Map<SensitivityId<?>, Sensitivity> sensitivities;

    protected MappedSensitivities(final Map<SensitivityId<?>, Sensitivity> sensitivities) {
        this.sensitivities = sensitivities;
    }

    @Override
    public <S extends Sensitivity> Optional<S> get(final SensitivityId<S> id) {
        return sensitivities.maybeGet(id).flatMap(id::convert);
    }

    @Override
    public ExplanationBuilder explain() {
        final ExplanationBuilder builder = this.explanationBuilder();
        sensitivities.forEach((id, sensitivity) -> builder.put(id.name(), sensitivity));
        return builder;
    }

}
