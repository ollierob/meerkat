package net.ollie.goat.data;

import net.meerkat.functions.Functions;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;

/**
 * @author ollie
 */
public interface BiProvider<K1, K2, V> extends Provider<KeyPair<K1, K2>, V> {

    @CheckForNull
    Element<V> getElement(K1 k1, K2 k2);

    @CheckForNull
    default V get(final K1 k1, final K2 k2) {
        return Functions.ifNonNull(this.getElement(k1, k2), Element::value);
    }

    @Override
    @Deprecated
    default Element<V> getElement(final KeyPair<K1, K2> pair) {
        return this.getElement(pair.left(), pair.right());
    }

    @Override
    default V get(final KeyPair<K1, K2> pair) {
        return Functions.ifNonNull(this.getElement(pair.left(), pair.right()), Element::value);
    }

    @Nonnull
    default <X extends Exception> V require(final K1 k1, final K2 k2, final BiFunction<? super K1, ? super K2, ? extends X> ifNull) throws X {
        final V value = this.get(k1, k2);
        if (value == null) {
            throw ifNull.apply(k1, k2);
        } else {
            return value;
        }
    }

    @Nonnull
    default V require(final K1 k1, final K2 k2) throws NoSuchElementException {
        return this.require(k1, k2, (a, b) -> new NoSuchElementException("No value for key " + a + "/" + b));
    }

}
