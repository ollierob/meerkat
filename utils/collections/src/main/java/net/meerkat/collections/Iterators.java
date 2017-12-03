package net.meerkat.collections;

import java.util.Iterator;
import java.util.function.Function;

/**
 * @author ollie
 */
public class Iterators {

    public static <T> Iterator<T> of(final T[] array) {
        return new Iterator<T>() {

            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public T next() {
                return array[i++];
            }

        };
    }

    public static <F, T> Iterator<T> transform(final Iterator<F> iterator, final Function<? super F, ? extends T> transformation) {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                return transformation.apply(iterator.next());
            }

        };

    }

}
