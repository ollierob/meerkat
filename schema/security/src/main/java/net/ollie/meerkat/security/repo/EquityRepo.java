package net.ollie.meerkat.security.repo;

import static java.util.Objects.requireNonNull;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.equity.Equity;
import net.ollie.meerkat.security.repo.dates.RepoDates;
import net.ollie.meerkat.security.repo.rate.RepoRate;

/**
 *
 * @author ollie
 */
public class EquityRepo extends AbstractRepo<Equity> {

    @XmlElementRef
    private Equity collateral;

    @Deprecated
    EquityRepo() {
    }

    public EquityRepo(
            final String name,
            final RepoRate rate,
            final Money principal,
            final Equity collateral,
            final RepoDates dates) {
        super(name, rate, principal, dates);
        this.collateral = requireNonNull(collateral);
    }

    @Override
    public Equity collateral() {
        return collateral;
    }

    @Override
    public <R> R handleWith(final Repo.Handler<R> handler) {
        return handler.handle(this);
    }

}
