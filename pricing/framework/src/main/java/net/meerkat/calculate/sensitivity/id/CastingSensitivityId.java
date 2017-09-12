package net.meerkat.calculate.sensitivity.id;

import java.util.Optional;

import net.meerkat.calculate.sensitivity.Sensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;

/**
 *
 * @author ollie
 */
public abstract class CastingSensitivityId<S extends Sensitivity> implements SensitivityId<S> {

    private final Class<S> clazz;

    protected CastingSensitivityId(Class<S> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Optional<S> convert(final Sensitivity sensitivity) {
        return clazz.isAssignableFrom(sensitivity.getClass())
                ? Optional.of(clazz.cast(sensitivity))
                : Optional.empty();
    }

}