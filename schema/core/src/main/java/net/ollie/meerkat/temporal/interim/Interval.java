package net.ollie.meerkat.temporal.interim;

import java.time.LocalDate;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class Interval implements Interim {

    @XmlAttribute(name = "start")
    private LocalDate startInclusive;

    @XmlAttribute(name = "end")
    private LocalDate endInclusive;

    @Deprecated
    Interval() {
    }

    public Interval(LocalDate startInclusive, LocalDate endInclusive) {
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
        return endInclusive.minusDays(1);
    }

    @Nonnull
    public LocalDate endInclusive() {
        return endInclusive;
    }

}
