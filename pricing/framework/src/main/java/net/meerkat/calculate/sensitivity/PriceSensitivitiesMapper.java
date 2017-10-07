package net.meerkat.calculate.sensitivity;

import java.util.Optional;
import java.util.function.Function;

import javax.annotation.Nonnull;

import net.coljate.collection.ImmutableCollection;
import net.coljate.map.ImmutableEntry;
import net.coljate.map.ImmutableMap;
import net.coljate.map.MutableMap;
import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 */
public interface PriceSensitivitiesMapper<V> extends ImmutableMap<SensitivityId<?>, Function<? super V, ? extends Sensitivity>> {

    @Nonnull
    default <S extends Sensitivity> Optional<S> get(final V provider, final SensitivityId<S> id) {
        return this.maybeGet(id)
                .map(func -> func.apply(provider))
                .flatMap(id::convert);
    }

    static <V> Builder<V> builder() {
        return new Builder<>();
    }

    class Builder<V> {

        private final MutableMap<SensitivityId<?>, Function<? super V, ? extends Sensitivity>> map = MutableMap.createHashMap();

        public <S extends Sensitivity> Builder<V> put(final SensitivityId<S> sensitivityId, final Function<? super V, ? extends S> function) {
            return this;
        }

        public PriceSensitivitiesMapper<V> build() {
            return new WrappedPriceSensitivitiesMap<>(map.immutableCopy());
        }

    }

    class WrappedPriceSensitivitiesMap<V> implements PriceSensitivitiesMapper<V> {

        private final ImmutableMap<SensitivityId<?>, Function<? super V, ? extends Sensitivity>> map;

        WrappedPriceSensitivitiesMap(final ImmutableMap<SensitivityId<?>, Function<? super V, ? extends Sensitivity>> map) {
            this.map = map;
        }

        @Override
        public ImmutableEntry<SensitivityId<?>, Function<? super V, ? extends Sensitivity>> getEntry(Object key) {
            return map.getEntry(key);
        }

        @Override
        public ImmutableSet<SensitivityId<?>> keys() {
            return map.keys();
        }

        @Override
        public ImmutableCollection<Function<? super V, ? extends Sensitivity>> values() {
            return map.values();
        }

        @Override
        public WrappedPriceSensitivitiesMap<V> with(final SensitivityId<?> key, Function<? super V, ? extends Sensitivity> value) {
            return new WrappedPriceSensitivitiesMap<>(map.with(key, value));
        }

    }

}
