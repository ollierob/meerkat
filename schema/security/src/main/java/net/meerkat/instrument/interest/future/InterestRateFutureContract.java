package net.meerkat.instrument.interest.future;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.money.Money;
import net.meerkat.money.currency.HasCurrency;
import net.meerkat.Explainable;

import org.apache.commons.math3.fraction.Fraction;

import net.meerkat.instrument.Instrument;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class InterestRateFutureContract
        implements Instrument, HasCurrency, Explainable {

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
    public CurrencyId currency() {
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
