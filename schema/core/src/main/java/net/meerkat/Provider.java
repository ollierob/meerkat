package net.meerkat;

import java.util.Objects;
import java.util.Optional;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Provider<K, V> {

    @CheckForNull
    V get(K key);

    @Nonnull
    default Optional<V> maybeGet(final K key) {
        return Optional.ofNullable(this.get(key));
    }

    @Nonnull
    default V require(final K key) {
        return Objects.requireNonNull(this.get(key), () -> "Missing [" + key + "]!");
    }

}
