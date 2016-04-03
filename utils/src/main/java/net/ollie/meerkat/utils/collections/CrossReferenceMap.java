package net.ollie.meerkat.utils.collections;

import com.google.common.collect.Iterables;

import java.util.Optional;
import java.util.Set;

/**
 *
 * @author ollie
 */
public interface CrossReferenceMap<K, V> {

    default Set<V> allOf(final K... keys) {
        return this.allOf(Sets.asUnmodifiableSet(keys));
    }

    Set<V> allOf(Set<K> keys);

    default Optional<V> onlyAllOf(final Set<K> keys) {
        final Set<V> all = this.allOf(keys);
        return Optional.ofNullable(Iterables.getOnlyElement(all, null));
    }

    Set<V> anyOf(Set<K> set);

    default Optional<V> onlyAnyOf(final Set<K> keys) {
        final Set<V> any = this.anyOf(keys);
        return Optional.ofNullable(Iterables.getOnlyElement(any, null));
    }

}
