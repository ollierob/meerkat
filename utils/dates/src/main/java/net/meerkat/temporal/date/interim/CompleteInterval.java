package net.meerkat.temporal.date.interim;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.stream.Stream;

/**
 * @author ollie
 */
public class CompleteInterval
        extends Interval
        implements List<LocalDate> {

    private static final long serialVersionUID = 1L;

    public CompleteInterval(final LocalDate startInclusive, final LocalDate endInclusive) {
        super(startInclusive, endInclusive);
    }

    @Override
    public boolean contains(final LocalDate date) {
        return !date.isBefore(this.startInclusive())
                && !date.isAfter(this.endInclusive());
    }

    @Override
    public Optional<CompleteInterval> closed() {
        return Optional.of(this);
    }

    @Override
    public LocalDate get(int index) {
        return this.startInclusive().plusDays(index);
    }

    @Override
    public Comparator<? super LocalDate> comparator() {
        return Comparator.naturalOrder();
    }

    @Override
    public SortedSet<LocalDate> subSet(LocalDate fromElement, LocalDate toElement) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SortedSet<LocalDate> headSet(LocalDate toElement) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SortedSet<LocalDate> tailSet(LocalDate fromElement) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LocalDate first() {
        return this.startInclusive();
    }

    @Override
    public LocalDate last() {
        return this.endInclusive();
    }

    @Override
    public int size() {
        return Math.toIntExact(ChronoUnit.DAYS.between(this.startInclusive(), this.endInclusive())) + 1;
    }

    @Override
    public Iterator<LocalDate> iterator() {
        return this.stream().iterator();
    }

    @Override
    public Stream<LocalDate> stream() {
        return Stream.iterate(this.startInclusive(), date -> date.plusDays(1)).limit(this.size());
    }

}
