package net.ollie.goat.data;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * @author Ollie
 */
public interface CompositeProvider<K1, K2, V, P extends Provider<K2, V>> extends Provider<K1, P> {

    @CheckForNull
    default V get(final K1 k1, final K2 k2) {
        final P provider = this.get(k1);
        return provider == null
                ? null
                : provider.get(k2);
    }

    @Nonnull
    default V require(final K1 k1, final K2 k2) {
        return this.require(k1).require(k2);
    }

}
