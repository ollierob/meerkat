package net.meerkat.calculate.sensitivity.id;

import java.util.Optional;

import net.meerkat.calculate.sensitivity.Sensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;

/**
 *
 * @author ollie
 */
public abstract class CastingSensitivityId<S extends Sensitivity> implements SensitivityId<S> {

    private final String name;
    private final Class<S> clazz;

    protected CastingSensitivityId(final String name, final Class<S> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Optional<S> convert(final Sensitivity sensitivity) {
        return sensitivity.as(clazz);
    }

}
