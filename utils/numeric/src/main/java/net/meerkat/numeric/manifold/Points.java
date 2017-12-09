package net.meerkat.numeric.manifold;

import net.coljate.list.List;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public interface Points<T> extends Iterable<T> {

    default boolean isEmpty() {
        return !this.iterator().hasNext();
    }

    default boolean isIncreasing(final Comparator<? super T> comparator) {
        final Iterator<T> iterator = this.iterator();
        if (!iterator.hasNext()) {
            return true;
        }
        T previous = iterator.next();
        while (iterator.hasNext()) {
            final T next = iterator.next();
            boolean increasing = comparator.compare(next, previous) >= 0;
            if (!increasing) {
                return false;
            }
            previous = next;
        }
        return true;
    }

    static <T> Points<T> of(final Collection<? extends T> collection) {
        return new ListPoints<T>(List.copyOf(collection));
    }

}
