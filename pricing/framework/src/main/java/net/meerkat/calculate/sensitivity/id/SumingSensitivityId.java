package net.meerkat.calculate.sensitivity.id;

import java.util.stream.Collector;

import net.meerkat.calculate.sensitivity.Sensitivity;

/**
 *
 * @author ollie
 */
public class SumingSensitivityId<S extends Sensitivity.Summing<S>> extends CastingSensitivityId<S> {

    private final S defaultValue;

    public SumingSensitivityId(final String name, final Class<S> clazz) {
        this(name, clazz, null);
    }

    public SumingSensitivityId(final String name, final Class<S> clazz, final S defaultValue) {
        super(name, clazz);
        this.defaultValue = defaultValue;
    }

    @Override
    public Collector<S, S, S> collector() {
        return Collector.of(
                () -> defaultValue,
                S::plus,
                S::plus);
    }

}
