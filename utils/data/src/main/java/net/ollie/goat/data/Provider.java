package net.ollie.goat.data;

import net.meerkat.functions.Functions;
import net.meerkat.functions.suppliers.Suppliers;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Function;

/**
 * @author Ollie
 */
public interface Provider<K, V> {

    @CheckForNull
    Element<V> getElement(K key);

    @CheckForNull
    default V get(K key) {
        return Functions.ifNonNull(this.getElement(key), Element::value);
    }

    default V getOrDefault(@Nonnull final K key, final V defaultValue) {
        return Suppliers.firstNonNull(this.get(key), defaultValue);
    }

    @Nonnull
    default Optional<V> maybeGet(final K key) {
        return Optional.ofNullable(this.get(key));
    }

    @Nonnull
    default V require(final K key) throws NoSuchElementException {
        return this.require(key, k -> new NoSuchElementException("No value associated with key [" + k + "]!"));
    }

    @Nonnull
    default <X extends Exception> V require(final K key, final Function<? super K, ? extends X> ifAbsent) throws X {
        final V value = this.get(key);
        if (value == null) {
            throw ifAbsent.apply(key);
        }
        return value;
    }

    @Nonnull
    default Map<K, V> getAll(final Set<K> keys) {
        final Map<K, V> all = new HashMap<>(keys.size());
        for (final K key : keys) {
            final V value = this.get(key);
            if (value != null) {
                all.put(key, value);
            }
        }
        return all;
    }

}
