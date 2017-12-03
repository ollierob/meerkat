package net.meerkat.collections;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Ollie
 */
public abstract class Maps {

    protected Maps() {
    }

    public static <K, V1, V2> Map<K, V2> lazilyTransformValues(final Map<K, V1> in, final Function<? super V1, ? extends V2> transform) {
        return new AbstractMap<K, V2>() {

            @Override
            public V2 get(final Object key) {
                final V1 value = in.get(key);
                return value == null ? null : transform.apply(value);
            }

            @Override
            public boolean containsKey(final Object key) {
                return in.containsKey(key);
            }

            @Override
            public Set<K> keySet() {
                return in.keySet();
            }

            @Override
            public Set<Map.Entry<K, V2>> entrySet() {
                return Sets.lazilyTransform(in.entrySet(), entry -> new SimpleImmutableEntry<>(entry.getKey(), transform.apply(entry.getValue())));
            }

            @Override
            public int size() {
                return in.size();
            }

        };
    }

    public static <K, V1, V2> Map<K, V2> eagerlyTransformValues(final Map<K, V1> in, final Function<? super V1, ? extends V2> transform) {
        final Map<K, V2> out = new HashMap<>(in.size());
        in.forEach((k, v) -> out.put(k, transform.apply(v)));
        return out;
    }

    public static <K, V> Map<K, V> eaglerlyGenerateValues(final Set<K> keys, final Function<? super K, ? extends V> transform) {
        final Map<K, V> map = new HashMap<>(keys.size());
        keys.forEach(key -> map.put(key, transform.apply(key)));
        return map;
    }

    public static <K, V> Map<K, V> lazilyGenerateValues(final Set<K> keys, final Function<? super K, ? extends V> transform) {
        return new AbstractMap<K, V>() {

            @Override
            public boolean containsKey(final Object key) {
                return keys.contains(key);
            }

            @Override
            public Set<K> keySet() {
                return keys;
            }

            @Override
            public V get(final Object key) {
                return keys.contains(key)
                        ? transform.apply((K) key)
                        : null;
            }

            @Override
            public Set<Map.Entry<K, V>> entrySet() {
                return Sets.lazilyTransform(keys, key -> new SimpleImmutableEntry<>(key, transform.apply(key)));
            }

            @Override
            public int size() {
                return keys.size();
            }

        };

    }

}
