package net.ollie.goat.data;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ollie
 */
public interface MutableProvider<K, V> extends Provider<K, V> {

    @CheckForNull
    V put(@Nonnull K key, @Nullable V value);

    @Nonnull
    default Map<K, V> putAll(@Nonnull final Map<? extends K, ? extends V> map) {
        if (map.isEmpty()) {
            return Collections.emptyMap();
        }
        final Map<K, V> out = new HashMap<>(map.size());
        map.forEach((key, value) -> out.put(key, this.put(key, value)));
        return out;
    }

}
