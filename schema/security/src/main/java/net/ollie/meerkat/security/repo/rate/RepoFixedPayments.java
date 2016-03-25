package net.ollie.meerkat.security.repo.rate;

import javax.xml.bind.annotation.XmlElement;

import net.ollie.meerkat.numeric.money.DecimalMoney;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public class RepoFixedPayments implements RepoRate {

    @XmlElement(name = "spot")
    private DecimalMoney spot;

    @XmlElement(name = "forward")
    private DecimalMoney forward;

    public RepoFixedPayments(Money spot, Money forward) {
        this.spot = spot.toDecimal();
        this.forward = forward.toDecimal();
    }

    public DecimalMoney spot() {
        return spot;
    }

    public DecimalMoney forward() {
        return forward;
    }

}
