package net.ollie.meerkat.security.interest.repo;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.money.Money;
import net.ollie.goat.numeric.percentage.DecimalPercentage;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.meerkat.identifier.security.SecurityIds;
import net.ollie.meerkat.security.bond.Bond;
import net.ollie.meerkat.security.interest.repo.dates.RepoDates;
import net.ollie.meerkat.security.interest.repo.rate.RepoRate;

/**
 *
 * @author ollie
 */
public class BondRepo extends AbstractRepo<Bond> {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "collateral", required = true)
    private Bond collateral;

    @XmlAttribute(name = "haircut", required = false)
    private Percentage haircut;

    @Deprecated
    BondRepo() {
    }

    public BondRepo(
            final String name,
            final SecurityIds identifiers,
            final RepoRate rate,
            final Bond collateral,
            final RepoDates dates,
            final Percentage haircut) {
        super(name, identifiers, rate, dates);
        this.collateral = collateral;
        this.haircut = haircut;
    }

    @Override
    public Bond collateral() {
        return collateral;
    }

    @Override
    public Money<?> principal() {
        return collateral.par().times(this.haircut().inverse().decimalValue());
    }

    @Nonnull
    public Percentage haircut() {
        return haircut == null
                ? DecimalPercentage.ZERO_PERCENT
                : haircut;
    }

    @Override
    public <R> R handleWith(final Repo.Handler<R> handler) {
        return handler.handle(this);
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("haircut", haircut)
                .put("collateral", collateral);
    }

}
