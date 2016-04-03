package net.ollie.meerkat.reporting;

import java.util.Optional;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Provider<K, V> {

    @CheckForNull
    V get(@Nonnull K key);

    default Optional<V> maybeGet(final K key) {
        return Optional.ofNullable(this.get(key));
    }

    default <V2> Provider<K, V2> andThen(final Provider<? super V, ? extends V2> provider) {
        return k -> this.maybeGet(k).map(provider::get).orElse(null);
    }

}
