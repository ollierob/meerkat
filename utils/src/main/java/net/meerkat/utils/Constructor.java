package net.meerkat.utils;

import java.util.function.Function;

/**
 *
 * @author ollie
 */
public abstract class Constructor<F, T> {

    public static <F, T> Constructor<F, T> of(final Function<? super F, ? extends T> constructor) {
        return new Constructor<F, T>() {

            @Override
            protected T construct(final F from) {
                return constructor.apply(from);
            }

        };
    }

    protected abstract T construct(F from);

}
