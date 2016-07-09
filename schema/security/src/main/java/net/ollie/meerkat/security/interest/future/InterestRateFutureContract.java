package net.ollie.meerkat.security.interest.future;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.money.Money;
import net.ollie.goat.money.currency.Currency;
import net.ollie.goat.money.currency.HasCurrency;
import net.ollie.meerkat.security.Security;

import org.apache.commons.math3.fraction.Fraction;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class InterestRateFutureContract implements Security, HasCurrency {

    @XmlElement(name = "accrual")
    private Fraction accrual;

    @XmlElement(name = "notional")
    private Money<?> notional;

    @Deprecated
    InterestRateFutureContract() {
    }

    public InterestRateFutureContract(Fraction accrual, Money<?> notional) {
        this.accrual = accrual;
        this.notional = notional;
    }

    public Fraction accrual() {
        return accrual;
    }

    @Override
    public Currency currency() {
        return notional.currency();
    }

    public Money<?> notional() {
        return notional;
    }

}
