package net.meerkat.time.calendar;

import net.coljate.list.List;
import net.coljate.map.Associative;
import net.coljate.sequence.Sequence;
import net.meerkat.temporal.date.HasDate;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.function.Predicate;

public interface TemporalSequence<T extends HasDate> extends Sequence<T>, Associative<LocalDate, T> {

    @Nonnull
    List<T> between(LocalDate startInclusive, LocalDate endExclusive);

    @Nonnull
    TemporalSequence<T> filter(Predicate<? super T> predicate);

    @Nonnull
    default TemporalSequence<T> onOrAfter(LocalDate startInclusive) {
        return this.filter(t -> !startInclusive.isAfter(t.date()));
    }

    @CheckForNull
    T latestBefore(LocalDate endExclusive);

}
