package net.meerkat.instrument.interest.future;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.Explainable;
import net.meerkat.instrument.Instrument;
import net.meerkat.money.Money;
import net.meerkat.money.currency.CurrencyId;

import org.apache.commons.math3.fraction.Fraction;

import net.meerkat.money.currency.HasCurrencyId;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class InterestRateFutureContract
        implements Instrument, HasCurrencyId, Explainable {

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
    public CurrencyId currencyId() {
        return notional.currencyId();
    }

    public Money<?> notional() {
        return notional;
    }

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("notional", notional)
                .put("years", accrual);
    }

}
