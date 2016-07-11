package net.ollie.meerkat.security.interest.future;

import com.google.common.collect.Iterables;

import java.util.Collections;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;

import net.ollie.meerkat.identifier.security.SecurityId;
import net.ollie.meerkat.identifier.security.SecurityIds;
import net.ollie.meerkat.security.derivative.forward.AbstractFuture;
import net.ollie.meerkat.security.derivative.forward.FutureDeliveryDates;
import net.ollie.meerkat.security.interest.InterestRateDerivative;

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
    private Set<SecurityIds> basket;

    @XmlElementRef(name = "delivery", required = true)
    private FutureDeliveryDates deliveryDates;

    @Deprecated
    BondFuture() {
    }

    public BondFuture(
            final String name,
            final SecurityIds identifiers,
            final Set<SecurityIds> basket,
            final FutureDeliveryDates deliveryDates) {
        super(name, identifiers);
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

    public Set<SecurityIds> basket() {
        return basket == null || basket.isEmpty()
                ? Collections.emptySet()
                : Collections.unmodifiableSet(basket);
    }

    public boolean isInBasket(final SecurityId id) {
        return Iterables.any(basket, ids -> ids.contains(id));
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
