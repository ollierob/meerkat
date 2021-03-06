package net.meerkat.calculate.sensitivity;

import net.meerkat.calculate.sensitivity.id.NonCollectingSensitivityId;
import net.meerkat.calculate.sensitivity.id.SumingSensitivityId;
import net.meerkat.objects.HasName;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.stream.Collector;

/**
 *
 * @author ollie
 */
public interface SensitivityId<S extends Sensitivity> extends HasSensitivityId, HasName {

    @Nonnull
    Collector<S, ?, S> collector();

    @Nonnull
    Optional<S> convert(Sensitivity sensitivity);

    @Override
    @Deprecated
    default SensitivityId<S> sensitivityId() {
        return this;
    }

    static <S extends Sensitivity> SensitivityId<S> nonSumming(final String name, final Class<S> clazz) {
        return new NonCollectingSensitivityId<>(name, clazz);
    }

    static <S extends Sensitivity.Summable<S>> SensitivityId<S> summing(final String name, final Class<S> clazz) {
        return new SumingSensitivityId<>(name, clazz);
    }

}
