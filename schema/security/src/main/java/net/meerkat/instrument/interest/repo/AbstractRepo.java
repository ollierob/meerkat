package net.meerkat.instrument.interest.repo;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.instrument.interest.repo.dates.RepoDates;
import net.meerkat.instrument.interest.repo.rate.RepoRate;
import net.meerkat.issuer.IssuerId;
import net.meerkat.instrument.Instrument;

/**
 *
 * @author ollie
 */
public abstract class AbstractRepo<C extends Instrument>
        extends IssuedSecurity
        implements Repo<C> {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "rate")
    private RepoRate rate;

    @XmlElementRef(name = "dates")
    private RepoDates dates;

    @Deprecated
    protected AbstractRepo() {
    }

    protected AbstractRepo(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuer,
            final RepoRate rate,
            final RepoDates dates) {
        super(name, identifiers, issuer);
        this.rate = rate;
        this.dates = dates;
    }

    @Override
    public RepoRate rate() {
        return rate;
    }

    @Override
    public RepoDates dates() {
        return dates;
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("dates", dates)
                .put("rate", rate);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(rate);
        out.writeObject(dates);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        rate = (RepoRate) in.readObject();
        dates = (RepoDates) in.readObject();
    }

}
