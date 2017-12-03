package net.meerkat.temporal.date.interim;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import java.util.AbstractList;
import java.util.SortedSet;
import java.util.Spliterator;

/**
 * @author ollie
 */
public abstract class Interval
        extends AbstractList<LocalDate>
        implements Interim, SortedSet<LocalDate>, Externalizable {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "start")
    private LocalDate startInclusive;

    @XmlAttribute(name = "end")
    private LocalDate endInclusive;

    @Deprecated
    protected Interval() {
    }

    protected Interval(LocalDate startInclusive, LocalDate endInclusive) {
        this.startInclusive = startInclusive;
        this.endInclusive = endInclusive;
    }

    @Nonnull
    public LocalDate startInclusive() {
        return startInclusive;
    }

    @Nonnull
    public LocalDate endInclusive() {
        return endInclusive;
    }

    @Nonnull
    public LocalDate endExclusive() {
        return endInclusive.plusDays(1);
    }

    @Override
    public Spliterator<LocalDate> spliterator() {
        return super.spliterator();
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
