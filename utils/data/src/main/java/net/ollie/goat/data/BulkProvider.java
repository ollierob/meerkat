package net.ollie.goat.data;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author Ollie
 */
public interface BulkProvider<K, V> extends Provider<K, V> {

    @Override
    default V get(final K key) {
        return this.getAll(Collections.singleton(key)).get(key);
    }

    @Override
    Map<K, V> getAll(Set<K> keys);

}
