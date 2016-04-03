package net.ollie.meerkat.reporting;

import java.util.Optional;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.ollie.meerkat.utils.Classes;

/**
 *
 * @author Ollie
 */
public interface Provider<K, V> {

    @CheckForNull
    V get(@Nonnull K key);

    @Nonnull
    default Optional<V> maybeGet(final K key) {
        return Optional.ofNullable(this.get(key));
    }

    @Nonnull
    default <S extends V> Optional<S> maybeGet(final K key, final Class<S> clazz) {
        return this.maybeGet(key).flatMap(value -> Classes.cast(value, clazz));
    }

    @Nonnull
    default <V2> Provider<K, V2> andThen(final Provider<? super V, ? extends V2> provider) {
        return k -> this.maybeGet(k).map(provider::get).orElse(null);
    }

}
