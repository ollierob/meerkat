package net.meerkat.instrument.interest.repo.rate;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.money.Money;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.meerkat.money.currency.CurrencyId;

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
