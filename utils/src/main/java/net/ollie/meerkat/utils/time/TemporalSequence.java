package net.ollie.meerkat.utils.time;

import java.time.temporal.Temporal;
import java.util.List;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.ollie.meerkat.utils.collections.Sequence;

/**
 *
 * @author Ollie
 */
public interface TemporalSequence<T extends Temporal, S> {

    boolean isEmpty();

    @Nonnull
    Sequence<S> onOrAfter(T time);

    @Nonnull
    List<S> between(T start, T end);

    @CheckForNull
    default S next(final T time) {
        return this.onOrAfter(time).first();
    }

}
