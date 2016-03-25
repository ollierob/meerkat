package net.ollie.meerkat.security.repo.rate;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public class RepoFixedPayments implements RepoRate {

    @XmlElementRef(name = "spot")
    private Money<?> spot;

    @XmlElementRef(name = "forward")
    private Money<?> forward;

    public RepoFixedPayments(final Money<?> spot, final Money<?> forward) {
        this.spot = spot;
        this.forward = forward;
    }

    public Money<?> spot() {
        return spot;
    }

    public Money<?> forward() {
        return forward;
    }

}
