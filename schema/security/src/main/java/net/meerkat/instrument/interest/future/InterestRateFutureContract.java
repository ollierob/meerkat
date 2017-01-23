package net.meerkat.instrument.interest.future;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.Explainable;
import net.meerkat.instrument.Instrument;
import net.meerkat.money.Money;
import net.meerkat.money.currency.CurrencyId;
import net.meerkat.money.currency.HasCurrency;

import org.apache.commons.math3.fraction.Fraction;

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
        return this.explanationBuilder()
                .put("notional", notional)
                .put("years", accrual);
    }

}
