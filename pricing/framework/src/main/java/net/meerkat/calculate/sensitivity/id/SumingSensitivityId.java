package net.meerkat.calculate.sensitivity.id;

import java.util.stream.Collector;

import net.meerkat.calculate.sensitivity.Sensitivity;

/**
 *
 * @author ollie
 */
public class SumingSensitivityId<S extends Sensitivity.Summing<S>> extends CastingSensitivityId<S> {

    private final S defaultValue;

    public SumingSensitivityId(final Class<S> clazz) {
        this(clazz, null);
    }

    public SumingSensitivityId(final Class<S> clazz, final S defaultValue) {
        super(clazz);
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
