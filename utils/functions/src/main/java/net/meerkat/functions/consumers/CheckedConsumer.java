package net.meerkat.functions.consumers;

import javax.annotation.Nullable;
import java.util.function.Consumer;

/**
 * @author Ollie
 * @see Consumer
 */
public interface CheckedConsumer<T, X extends Exception> {

    void accept(@Nullable T t) throws X;

}
