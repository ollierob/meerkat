package net.meerkat.money.interest.earning;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.money.Money;
import net.meerkat.money.interest.fixed.FixedInterestRate;

/**
 *
 * @author ollie
 */
public class FixedInterestEarning implements InterestEarning.Fixed {

    @XmlElementRef(name = "notional")
    private Money<?> notional;

    @XmlElementRef(name = "rate")
    private FixedInterestRate rate;

    @Deprecated
    protected FixedInterestEarning() {
    }

    protected FixedInterestEarning(final Money<?> notional, final FixedInterestRate rate) {
        this.notional = notional;
        this.rate = rate;
    }

    @Override
    public Money<?> notional() {
        return notional;
    }

    @Override
    public FixedInterestRate rate() {
        return rate;
    }

}
