package net.meerkat.money.interest.earning;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.money.Money;
import net.meerkat.money.interest.fixed.FixedInterestRate;

/**
 *
 * @author ollie
 */
public class DefaultFixedInterestEarning implements FixedInterestEarning {

    @XmlElementRef(name = "notional")
     Money<?> notional;

    @XmlElementRef(name = "rate")
     FixedInterestRate rate;

    @Deprecated
    protected DefaultFixedInterestEarning() {
    }

    protected DefaultFixedInterestEarning(final Money<?> notional, final FixedInterestRate rate) {
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
