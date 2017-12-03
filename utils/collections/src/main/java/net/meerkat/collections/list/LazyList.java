package net.meerkat.collections.list;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.AbstractList;
import java.util.function.IntFunction;

/**
 * @author Ollie
 */
@NotThreadSafe
public class LazyList<T> extends AbstractList<T> {

    private final Object[] store;
    private final IntFunction<? extends T> func;

    protected LazyList(final int size, final IntFunction<? extends T> func) {
        this.store = new Object[size];
        this.func = func;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(final int index) {
        T got = (T) store[index];
        if (got == null) {
            got = func.apply(index);
            store[index] = got;
        }
        return got;
    }

    @Override
    public int size() {
        return store.length;
    }

}
