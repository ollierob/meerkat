package net.ollie.goat.data;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.function.Function;

/**
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class Element<V> {

    private static final Element NULL = new Element(null);

    public static <V> Element<V> ofNullable(final V value) {
        return value == null
                ? NULL
                : new Element<>(value);
    }

    private final V value;

    private Element(final V value) {
        this.value = value;
    }

    @CheckForNull
    public V value() {
        return value;
    }

    @Nonnull
    @CheckForNull
    public <T> Element<T> map(final Function<? super V, ? extends T> transform) {
        return value == null
                ? (Element<T>) this
                : ofNullable(transform.apply(value));
    }

}
