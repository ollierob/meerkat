package net.ollie.meerkat.time.interim;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.utils.collections.FiniteSequence;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class Interval
        extends FiniteSequence<LocalDate>
        implements Interim, Externalizable {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "start")
    private LocalDate startInclusive;

    @XmlAttribute(name = "end")
    private LocalDate endInclusive;

    @Deprecated
    Interval() {
    }

    public Interval(final LocalDate startInclusive, final LocalDate endInclusive) {
        this.startInclusive = startInclusive;
        this.endInclusive = endInclusive;
    }

    @Override
    public boolean contains(final LocalDate date) {
        return !date.isBefore(startInclusive)
                && !date.isAfter(endInclusive);
    }

    @Override
    public Optional<Interval> closed() {
        return Optional.of(this);
    }

    @Nonnull
    public LocalDate startInclusive() {
        return startInclusive;
    }

    @Nonnull
    public LocalDate endExclusive() {
        return endInclusive.plusDays(1);
    }

    @Nonnull
    public LocalDate endInclusive() {
        return endInclusive;
    }

    @Override
    public LocalDate get(final int index) {
        return startInclusive.plusDays(index);
    }

    @Override
    public int size() {
        return Math.toIntExact(ChronoUnit.DAYS.between(startInclusive, endInclusive)) + 1;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(startInclusive);
        out.writeObject(endInclusive);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        startInclusive = (LocalDate) in.readObject();
        endInclusive = (LocalDate) in.readObject();
    }

}
