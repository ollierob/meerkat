package net.ollie.meerkat.security.interest.repo.rate;

import static java.util.Objects.requireNonNull;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.money.interest.fixed.FixedInterestRate;

/**
 *
 * @author ollie
 */
public class RepoInterestRate implements RepoRate {

    @XmlElementRef(name = "rate")
    private FixedInterestRate rate;

    @Deprecated
    RepoInterestRate() {
    }

    public RepoInterestRate(@Nonnull final FixedInterestRate rate) {
        this.rate = requireNonNull(rate);
    }

    @Override
    public FixedInterestRate rate() {
        return rate;
    }

    @Override
    public String toString() {
        return rate.toString();
    }

}
