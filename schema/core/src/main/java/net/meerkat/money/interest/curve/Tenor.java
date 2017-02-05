package net.meerkat.money.interest.curve;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.Period;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.Explainable;
import net.ollie.goat.temporal.date.Periods;
import net.ollie.goat.temporal.date.years.Years;

/**
 * A normalized {@link Period}.
 *
 * @author ollie
 */
@XmlRootElement
public class Tenor implements Explainable, Externalizable, Comparable<Tenor> {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "period")
    private Period period;

    @Deprecated
    Tenor() {
    }

    public Tenor(@Nonnull final Period period) {
        this.period = period.normalized();
    }

    public Period period() {
        return period;
    }

    @Override
    public ExplanationBuilder explain() {
        return this.explanationBuilder()
                .put("period", period);
    }

    public Years years() {
        return Years.of(period);
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(period);
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        period = (Period) in.readObject();
    }

    @Override
    public int compareTo(final Tenor that) {
        return Periods.APPROXIMATE_PERIOD_COMPARATOR.compare(this.period, that.period);
    }

}
