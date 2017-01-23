package net.meerkat.instrument.interest.future;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;

import net.coljate.set.Set;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.derivative.forward.AbstractFuture;
import net.meerkat.instrument.derivative.forward.FutureDeliveryDates;
import net.meerkat.instrument.interest.InterestRateDerivative;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author ollie
 */
public class BondFuture
        extends AbstractFuture<BondFutureContract>
        implements InterestRateDerivative {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "contract")
    private BondFutureContract contract;

    @XmlElementWrapper(name = "basket")
    @XmlElement(name = "bond", required = true)
    private Set<InstrumentIds> basket;

    @XmlElementRef(name = "delivery", required = true)
    private FutureDeliveryDates deliveryDates;

    @Deprecated
    BondFuture() {
    }

    public BondFuture(
            final String name,
            final InstrumentIds identifiers,
            final Set<InstrumentIds> basket,
            final FutureDeliveryDates deliveryDates,
            final IssuerId issuer) {
        super(name, identifiers, issuer);
        this.basket = basket;
        this.deliveryDates = deliveryDates;
    }

    @Override
    public BondFutureContract underlying() {
        return contract;
    }

    @Override
    public FutureDeliveryDates dates() {
        return deliveryDates;
    }

    public Set<InstrumentIds> basket() {
        return basket == null
                ? Set.of()
                : basket.immutableCopy();
    }

    public boolean isInBasket(final InstrumentId id) {
        return basket.anyMatch(ids -> ids.contains(id));
    }

    @Override
    public <R> R handleWith(final InterestRateDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("basket", basket)
                .put("contract", contract)
                .put("dates", deliveryDates);
    }

}
