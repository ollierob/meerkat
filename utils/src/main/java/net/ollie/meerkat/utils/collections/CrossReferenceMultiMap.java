package net.ollie.meerkat.utils.collections;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ollie
 */
public class CrossReferenceMultiMap<K, V> implements CrossReferenceMap<K, V> {

    private final SetMultimap<K, V> multimap;

    public CrossReferenceMultiMap() {
        this(HashMultimap.create());
    }

    public CrossReferenceMultiMap(final SetMultimap<K, V> multimap) {
        this.multimap = multimap;
    }

    @Override
    public Set<V> allOf(final Set<K> set) {
        Set<V> all = null;
        for (final K key : set) {
            final Set<V> values = multimap.get(key);
            if (all == null) {
                all = new HashSet<>(values);
            } else {
                all.retainAll(values);
            }
            if (all.isEmpty()) {
                break;
            }
        }
        return all == null
                ? java.util.Collections.emptySet()
                : java.util.Collections.unmodifiableSet(all);
    }

    @Override
    public Set<V> anyOf(final Set<K> set) {
        final Set<V> any = new HashSet<>();
        set.forEach(key -> any.addAll(multimap.get(key)));
        return any;
    }

    public void put(final K key, final V value) {
        multimap.put(key, value);
    }

    public void putAll(final Set<? extends K> keys, final V value) {
        keys.forEach(key -> this.put(key, value));
    }

}
