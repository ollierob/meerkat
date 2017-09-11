package net.meerkat.calculate.sensitivity;

import net.meerkat.calculate.sensitivity.id.NonCollectingSensitivityId;
import net.meerkat.calculate.sensitivity.id.SumingSensitivityId;

import java.util.Optional;
import java.util.stream.Collector;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface SensitivityId<S extends Sensitivity> extends HasSensitivityId {

    @Nonnull
    Collector<S, ?, S> collector();

    @Nonnull
    default Optional<S> convert(final Sensitivity sensitivity) {
        return Optional.of((S) sensitivity);
    }

    @Override
    @Deprecated
    default SensitivityId<S> sensitivityId() {
        return this;
    }

    static <S extends Sensitivity> SensitivityId<S> nonSumming(final Class<S> clazz) {
        return new NonCollectingSensitivityId<>(clazz);
    }

    static <S extends Sensitivity.Summing<S>> SensitivityId<S> summing(final Class<S> clazz) {
        return new SumingSensitivityId<>(clazz);
    }

}
