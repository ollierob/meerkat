package net.ollie.meerkat.numeric.interest.earning;

import java.util.function.Function;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.numeric.interest.FixedInterestRate;
import net.ollie.goat.money.interest.InterestRate;
import net.ollie.goat.money.Money;
import net.ollie.goat.money.interest.InterestRateId;

/**
 *
 * @author ollie
 */
public class FixedInterestEarning implements InterestEarning {

    @XmlElementRef(name = "notional")
    private Money notional;

    @XmlElementRef(name = "rate")
    private FixedInterestRate rate;

    @Deprecated
    protected FixedInterestEarning() {
    }

    protected FixedInterestEarning(final Money notional, final FixedInterestRate rate) {
        this.notional = notional;
        this.rate = rate;
    }

    @Override
    public Money notional() {
        return notional;
    }

    public FixedInterestRate rate() {
        return rate;
    }

    @Override
    @Deprecated
    public InterestRate rate(Function<? super InterestRateId, ? extends InterestRate> getRate) {
        return this.rate();
    }

}
