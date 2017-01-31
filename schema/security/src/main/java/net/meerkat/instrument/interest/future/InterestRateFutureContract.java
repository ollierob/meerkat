package net.meerkat.instrument.interest.future;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.Explainable;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.money.Money;
import net.ollie.goat.temporal.date.count.DayCount;


/**
 *
 * @author ollie
 */
@XmlRootElement
public class InterestRateFutureContract
        extends NamedInstrument
        implements Instrument, HasCurrencyId, Explainable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "accrual")
    private DayCount accrual;

    @XmlElement(name = "notional")
    private Money<?> notional;

    @Deprecated
    InterestRateFutureContract() {
    }

    public InterestRateFutureContract(
            final String name,
            final InstrumentIds instrumentIds,
            final DayCount accrual,
            final Money<?> notional) {
        super(name, instrumentIds);
        this.accrual = accrual;
        this.notional = notional;
    }

    public DayCount accrual() {
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
    public ExplanationBuilder explain() {
        return super.explain()
                .put("notional", notional)
                .put("years", accrual);
    }

}