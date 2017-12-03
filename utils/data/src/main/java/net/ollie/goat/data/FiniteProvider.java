package net.ollie.goat.data;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * @author ollie
 */
public interface FiniteProvider<K, V> extends Provider<K, V> {

    @Nonnull
    Map<K, V> getAll();

    @Override
    default V get(final K key) {
        return this.getAll().get(key);
    }

}
