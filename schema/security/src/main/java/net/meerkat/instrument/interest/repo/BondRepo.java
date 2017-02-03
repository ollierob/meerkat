package net.meerkat.instrument.interest.repo;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.instrument.interest.repo.dates.RepoDates;
import net.meerkat.instrument.interest.repo.rate.RepoRate;
import net.meerkat.issue.Issue;
import net.meerkat.money.Money;
import net.ollie.goat.numeric.percentage.DecimalPercentage;
import net.ollie.goat.numeric.percentage.Percentage;

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
            final InstrumentIds identifiers,
            final Issue issue,
            final RepoRate rate,
            final Bond collateral,
            final RepoDates dates,
            final Percentage haircut) {
        super(name, identifiers, issue, rate, dates);
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
