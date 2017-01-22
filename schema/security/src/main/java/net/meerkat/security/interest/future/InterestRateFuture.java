package net.meerkat.security.interest.future;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.money.currency.Currency;
import net.meerkat.money.currency.HasCurrency;
import net.meerkat.identifier.security.SecurityIds;
import net.meerkat.security.derivative.forward.AbstractFuture;
import net.meerkat.security.derivative.forward.FutureDeliveryDates;
import net.meerkat.security.interest.InterestRateDerivative;

/**
 *
 * @author Ollie
 */
public class InterestRateFuture
        extends AbstractFuture<InterestRateFutureContract>
        implements InterestRateDerivative, HasCurrency {

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
            final SecurityIds identifiers,
            final InterestRateFutureContract contract,
            final FutureDeliveryDates dates) {
        super(name, identifiers);
        this.contract = contract;
        this.dates = dates;
    }

    @Override
    public FutureDeliveryDates dates() {
        return dates;
    }

    @Override
    public Currency currency() {
        return contract.currency();
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