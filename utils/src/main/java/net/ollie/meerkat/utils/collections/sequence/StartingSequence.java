package net.ollie.meerkat.utils.collections.sequence;

import javax.annotation.CheckForNull;

/**
 *
 * @author ollie
 */
public interface StartingSequence<T> extends Sequence<T> {

    @CheckForNull
    T first();

}
