package net.meerkat.calculate.sensitivity;

import java.util.Optional;
import java.util.stream.Collector;

import javax.annotation.Nonnull;

import net.meerkat.calculate.sensitivity.id.NonCollectingSensitivityId;
import net.meerkat.calculate.sensitivity.id.SumingSensitivityId;

/**
 *
 * @author ollie
 */
public interface SensitivityId<S extends Sensitivity> extends HasSensitivityId {

    @Nonnull
    Collector<S, ?, S> collector();

    @Nonnull
    Optional<S> convert(Sensitivity sensitivity);

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
