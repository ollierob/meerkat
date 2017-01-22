package net.meerkat.security.interest.future;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.money.Money;
import net.ollie.meerkat.money.currency.Currency;
import net.ollie.meerkat.money.currency.HasCurrency;
import net.ollie.meerkat.Explainable;
import net.ollie.meerkat.security.Security;

import org.apache.commons.math3.fraction.Fraction;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class InterestRateFutureContract
        implements Security, HasCurrency, Explainable {

    @XmlElement(name = "accrual")
    private Fraction accrual;

    @XmlElement(name = "notional")
    private Money<?> notional;

    @Deprecated
    InterestRateFutureContract() {
    }

    public InterestRateFutureContract(final Fraction accrual, final Money<?> notional) {
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

    @Override
    public Map<String, Object> explain() {
        return new ExplanationBuilder()
                .put("notional", notional)
                .put("years", accrual);
    }

}
