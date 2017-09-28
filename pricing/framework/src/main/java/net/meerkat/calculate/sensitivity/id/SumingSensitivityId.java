package net.meerkat.calculate.sensitivity.id;

import java.util.stream.Collector;

import javax.annotation.CheckForNull;

import net.meerkat.calculate.sensitivity.Sensitivity;

/**
 *
 * @author ollie
 */
public class SumingSensitivityId<S extends Sensitivity.Summable<S>> extends CastingSensitivityId<S> {

    private final S defaultValue;

    public SumingSensitivityId(final String name, final Class<S> clazz) {
        this(name, clazz, null);
    }

    public SumingSensitivityId(final String name, final Class<S> clazz, final S defaultValue) {
        super(name, clazz);
        this.defaultValue = defaultValue;
    }

    @CheckForNull
    protected S defaultValue() {
        return defaultValue;
    }

    @Override
    public Collector<S, S, S> collector() {
        return Collector.of(
                this::defaultValue,
                SumingSensitivityId::add,
                SumingSensitivityId::add);
    }

    static <S extends Sensitivity.Summable<S>> S add(final S left, final S right) {
        return left == null ? right : left.plus(right);
    }

}
