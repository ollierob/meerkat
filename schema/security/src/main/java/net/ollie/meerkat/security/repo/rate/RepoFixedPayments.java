package net.ollie.meerkat.security.repo.rate;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.goat.currency.CurrencyId;
import net.ollie.meerkat.numeric.interest.FixedInterestRate;
import net.ollie.goat.money.Money;

/**
 *
 * @author ollie
 */
public class RepoFixedPayments<C extends CurrencyId> implements RepoRate {

    @XmlElementRef(name = "spot")
    private Money<C> spot;

    @XmlElementRef(name = "forward")
    private Money<C> forward;

    @Deprecated
    RepoFixedPayments() {
    }

    public RepoFixedPayments(final Money<C> spot, final Money<C> forward) {
        this.spot = spot;
        this.forward = forward;
    }

    public Money<C> spot() {
        return spot;
    }

    public Money<C> forward() {
        return forward;
    }

    @Override
    public FixedInterestRate rate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isSpecial() {
        return forward.compareTo(spot) < 0;
    }

}
