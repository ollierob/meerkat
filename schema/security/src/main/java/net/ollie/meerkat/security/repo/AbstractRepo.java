package net.ollie.meerkat.security.repo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.NamedSecurity;
import net.ollie.meerkat.security.Security;
import net.ollie.meerkat.security.repo.dates.RepoDates;
import net.ollie.meerkat.security.repo.rate.RepoRate;

/**
 *
 * @author ollie
 */
public abstract class AbstractRepo<C extends Security>
        extends NamedSecurity
        implements Repo<C> {

    @XmlElementRef(name = "rate")
    private RepoRate rate;

    @XmlElement(name = "principal")
    private Money<?> principal;

    @XmlElementRef(name = "dates")
    private RepoDates dates;

    @Deprecated
    protected AbstractRepo() {
    }

    protected AbstractRepo(
            final String name,
            final RepoRate rate,
            final Money<?> principal,
            final RepoDates dates) {
        super(name);
        this.rate = rate;
        this.principal = principal;
        this.dates = dates;
    }

    @Override
    public RepoRate rate() {
        return rate;
    }

    @Override
    public Money<?> principal() {
        return principal;
    }

    @Override
    public RepoDates dates() {
        return dates;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()
                + ": principal [" + principal
                + "] collateral [" + this.collateral().name()
                + "] rate [" + rate
                + "] dates [" + dates
                + "] ";
    }

}
