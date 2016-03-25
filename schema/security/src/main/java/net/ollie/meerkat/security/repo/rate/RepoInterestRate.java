package net.ollie.meerkat.security.repo.rate;

import static java.util.Objects.requireNonNull;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.numeric.interest.FixedInterestRate;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author ollie
 */
public class RepoInterestRate implements RepoRate {

    @XmlElementRef
    private FixedInterestRate rate;

    @Deprecated
    RepoInterestRate() {
    }

    public RepoInterestRate(@Nonnull final FixedInterestRate rate) {
        this.rate = requireNonNull(rate);
    }

    @Nonnull
    public FixedInterestRate rate() {
        return rate;
    }

    public boolean isSpecial() {
        return rate.isNegative();
    }

    @Override
    public String toString() {
        return rate.toString();
    }

}
