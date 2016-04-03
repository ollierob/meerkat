package net.ollie.meerkat.security.repo;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.Bond;
import net.ollie.meerkat.security.repo.dates.RepoDates;
import net.ollie.meerkat.security.repo.rate.RepoRate;

/**
 *
 * @author ollie
 */
public class BondRepo extends AbstractRepo<Bond> {

    @XmlElementRef(name = "collateral", required = true)
    private Bond collateral;

    @XmlAttribute(name = "haircut", required = false)
    private Percentage haircut;

    @Deprecated
    BondRepo() {
    }

    public BondRepo(
            final String name,
            final RepoRate rate,
            final Bond collateral,
            final RepoDates dates,
            final Percentage haircut) {
        super(name, rate, dates);
        this.collateral = collateral;
        this.haircut = haircut;
    }

    @Override
    public Bond collateral() {
        return collateral;
    }

    @Override
    public Money<?> principal() {
        return collateral.par().times(this.haircut().inverse());
    }

    @Nonnull
    public Percentage haircut() {
        return haircut == null
                ? Percentage.ZERO_PERCENT
                : haircut;
    }

    @Override
    public <R> R handleWith(final Repo.Handler<R> handler) {
        return handler.handle(this);
    }

    @Override
    public String toString() {
        return super.toString() + " haircut [" + this.haircut() + "]";
    }

}
