package net.meerkat.utils.collections.sequence;

import javax.annotation.Nonnull;

/**
 * A sequence that starts
 *
 * @author Ollie
 */
public interface OrderedSequence<K, S> extends Sequence<S> {

    @Nonnull
    StartingSequence<S> onOrAfter(K key);

    @Nonnull
    FiniteSequence<S> between(K start, K end);

}
