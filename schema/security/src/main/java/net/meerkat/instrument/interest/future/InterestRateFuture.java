package net.meerkat.instrument.interest.future;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.derivative.forward.AbstractFuture;
import net.meerkat.instrument.derivative.forward.FutureDeliveryDates;
import net.meerkat.instrument.interest.InterestRateDerivative;
import net.meerkat.issue.Issue;

/**
 *
 * @author Ollie
 */
public class InterestRateFuture
        extends AbstractFuture<InterestRateFutureContract>
        implements InterestRateDerivative, HasCurrencyId {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "contract")
    private InterestRateFutureContract contract;

    @XmlElementRef(name = "dates")
    private FutureDeliveryDates dates;

    @Deprecated
    protected InterestRateFuture() {
    }

    public InterestRateFuture(
            final String name,
            final InstrumentIds identifiers,
            final InterestRateFutureContract contract,
            final FutureDeliveryDates dates,
            final Issue issue) {
        super(name, identifiers, issue);
        this.contract = contract;
        this.dates = dates;
    }

    @Override
    public FutureDeliveryDates dates() {
        return dates;
    }

    @Override
    public CurrencyId currencyId() {
        return contract.currencyId();
    }

    @Override
    public InterestRateFutureContract underlying() {
        return contract;
    }

    @Override
    public <R> R handleWith(final InterestRateDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("dates", dates)
                .put("contract", contract);
    }

}
