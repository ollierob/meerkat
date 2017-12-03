package net.ollie.goat.data;

/**
 * @author Ollie
 */
public class CachingProvider<K, V> implements Provider<K, V> {

    private final Provider<K, V> source;
    private final MutableProvider<K, V> cache;

    public CachingProvider(final Provider<K, V> source, final MutableProvider<K, V> cache) {
        this.source = source;
        this.cache = cache;
    }

    @Override
    public Element<V> getElement(final K key) {
        Element<V> element = cache.getElement(key);
        if (element == null) {
            element = source.getElement(key);
            cache.put(key, element == null ? null : element.value());
        }
        return element;
    }

}
