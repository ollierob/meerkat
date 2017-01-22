package net.meerkat.security.interest.repo;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.identifier.security.SecurityIds;
import net.meerkat.security.NamedSecurity;
import net.ollie.meerkat.security.Security;
import net.meerkat.security.interest.repo.dates.RepoDates;
import net.meerkat.security.interest.repo.rate.RepoRate;

/**
 *
 * @author ollie
 */
public abstract class AbstractRepo<C extends Security>
        extends NamedSecurity
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
            final SecurityIds identifiers,
            final RepoRate rate,
            final RepoDates dates) {
        super(name, identifiers);
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
