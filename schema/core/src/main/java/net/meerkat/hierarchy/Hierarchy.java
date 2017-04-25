package net.meerkat.hierarchy;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.meerkat.utils.time.Timestampable;

/**
 *
 * @author Ollie
 */
public interface Hierarchy<T> extends Timestampable<Hierarchy<T>> {

    @Nonnull
    T base();

    @CheckForNull
    T parent();

    @CheckForNull
    T ultimateParent();

}
