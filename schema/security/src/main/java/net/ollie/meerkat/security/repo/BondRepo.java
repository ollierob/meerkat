package net.ollie.meerkat.security.repo;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.Bond;
import net.ollie.meerkat.security.repo.dates.RepoDates;
import net.ollie.meerkat.security.repo.rate.RepoRate;

/**
 *
 * @author ollie
 */
public class BondRepo extends AbstractRepo<Bond> {

    @XmlElementRef
    private Bond collateral;

    @Deprecated
    BondRepo() {
    }

    public BondRepo(
            final String name,
            final RepoRate rate,
            final Bond collateral,
            final RepoDates dates) {
        super(name, rate, dates);
        this.collateral = collateral;
    }

    @Override
    public Bond collateral() {
        return collateral;
    }

    @Override
    public Money<?> principal() {
        return collateral.nominal().par();
    }

    @Override
    public <R> R handleWith(final Repo.Handler<R> handler) {
        return handler.handle(this);
    }

}
