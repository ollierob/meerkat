package net.ollie.goat.collectors;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;

import static java.util.stream.Collectors.toList;

/**
 * @author Ollie
 */
public final class PairCollectors {

    private PairCollectors() {
    }

    public static <F, T> Collector<F, ?, List<T>> pairs(final BiFunction<? super F, ? super F, ? extends T> toPair) {
        return pairs(toPair, toList());
    }

    public static <F, T, C extends Collection<T>, A> Collector<F, A, C> pairs(final BiFunction<? super F, ? super F, ? extends T> toPair, final Collector<T, A, C> collector) {
        return new Collector<F, A, C>() {

            @Override
            public Supplier<A> supplier() {
                return collector.supplier();
            }

            @Override
            public BiConsumer<A, F> accumulator() {
                return new BiConsumer<A, F>() {

                    final BiConsumer<A, T> accumulator = collector.accumulator();
                    F previous = null;
                    boolean first = true;

                    @Override
                    public void accept(final A accumulated, final F next) {
                        if (!first) {
                            final T pair = toPair.apply(previous, next);
                            accumulator.accept(accumulated, pair);
                        }
                        first = false;
                        previous = next;
                    }

                };
            }

            @Override
            public BinaryOperator<A> combiner() {
                return collector.combiner();
            }

            @Override
            public Function<A, C> finisher() {
                return collector.finisher();
            }

            @Override
            public Set<Collector.Characteristics> characteristics() {
                return Collections.emptySet();
            }

        };
    }

}
