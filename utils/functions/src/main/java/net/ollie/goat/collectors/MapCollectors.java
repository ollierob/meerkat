package net.ollie.goat.collectors;

import net.ollie.goat.functions.CheckedFunction;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * @author Ollie
 */
public final class MapCollectors {

    private MapCollectors() {
    }

    private static <K, V, M extends Map<K, V>> M putAllIntoLeft(final M left, final Map<? extends K, ? extends V> right) {
        left.putAll(right);
        return left;
    }

    public static <K, V> Collector<V, ?, Map<K, V>> mapLastKey(final Function<? super V, ? extends K> toKey) {
        return Collector.<V, Map<K, V>, Map<K, V>>of(
                HashMap::new,
                (map, value) -> map.put(toKey.apply(value), value),
                MapCollectors::putAllIntoLeft,
                Function.identity());
    }

    public static <K, V> Collector<K, ?, Map<K, V>> mapLastValue(final Function<? super K, ? extends V> toValue) {
        return mapLastValue(toValue, HashMap::new);
    }

    public static <K, V, M extends Map<K, V>> Collector<K, ?, M> mapLastValue(final Function<? super K, ? extends V> toValue, final Supplier<M> mapSupplier) {
        return Collector.of(
                mapSupplier,
                (map, key) -> map.put(key, toValue.apply(key)),
                MapCollectors::putAllIntoLeft,
                Function.identity());
    }

    public static <K, V, X extends Exception> Collector<K, ?, Map<K, V>> mapLastValue(final CheckedFunction<? super K, ? extends V, X> toValue, final Consumer<X> onException) {
        return mapLastValue(toValue, HashMap::new, onException);
    }

    public static <K, V, X extends Exception, M extends Map<K, V>> Collector<K, ?, M> mapLastValue(
            final CheckedFunction<? super K, ? extends V, X> toValue,
            final Supplier<M> createMap,
            final Consumer<X> onException) {
        return new Collector<K, M, M>() {

            @Override
            public Supplier<M> supplier() {
                return createMap;
            }

            @Override
            public BiConsumer<M, K> accumulator() {
                return (map, key) -> {
                    try {
                        final V value = toValue.apply(key);
                        map.put(key, value);
                    } catch (final RuntimeException rex) {
                        throw rex;
                    } catch (final Exception ex) {
                        onException.accept((X) ex);
                    }
                };
            }

            @Override
            public BinaryOperator<M> combiner() {
                return (left, right) -> {
                    left.putAll(right);
                    return left;
                };
            }

            @Override
            public Function<M, M> finisher() {
                return Function.identity();
            }

            @Override
            public Set<Collector.Characteristics> characteristics() {
                return Collections.singleton(Collector.Characteristics.IDENTITY_FINISH);
            }

        };
    }

}
