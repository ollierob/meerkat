package net.meerkat.utils;

import java.util.function.Function;

/**
 * Class to aid importing of protected methods into other packages.
 *
 * @author ollie
 */
public abstract class Protected<F, T> {

    public static <F, T> Protected<F, T> of(final Function<? super F, ? extends T> constructor) {
        return new Protected<F, T>() {

            @Override
            protected T construct(final F from) {
                return constructor.apply(from);
            }

        };
    }

    protected abstract T construct(F from);

}
