package net.ollie.meerkat.numeric.interest.earning;

import java.util.function.Function;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.InterestRate;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.numeric.interest.InterestRateId;

/**
 *
 * @author ollie
 */
public class FloatingInterestEarning implements InterestEarning {

    @XmlElementRef(name = "notional")
    private Money notional;

    @XmlAttribute(name = "spread")
    private Percentage spread;

    @XmlElementRef(name = "basis")
    private InterestRateId basis;

    protected FloatingInterestEarning() {
    }

    public FloatingInterestEarning(Money notional, Percentage spread, InterestRateId basis) {
        this.notional = notional;
        this.spread = spread;
        this.basis = basis;
    }

    @Override
    public Money notional() {
        return notional;
    }

    @Override
    public InterestRate rate(final Function<? super InterestRateId, ? extends InterestRate> getRate) {
        return getRate.apply(basis).plus(spread);
    }

}
