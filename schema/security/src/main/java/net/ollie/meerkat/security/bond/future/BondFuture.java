package net.ollie.meerkat.security.bond.future;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.identifier.security.SecurityIds;
import net.ollie.meerkat.utils.numeric.Percentage;
import net.ollie.meerkat.security.bond.BondDerivative;
import net.ollie.meerkat.security.derivative.forward.AbstractFuture;
import net.ollie.meerkat.security.derivative.forward.FutureDeliveryDates;

/**
 *
 * @author ollie
 */
public class BondFuture
        extends AbstractFuture<BondFutureBasket>
        implements BondDerivative<BondFutureBasket> {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "basket", required = true)
    private BondFutureBasket basket;

    @XmlElementRef(name = "delivery", required = true)
    private FutureDeliveryDates deliveryDates;

    @Deprecated
    BondFuture() {
    }

    public BondFuture(
            final String name,
            final SecurityIds identifiers,
            final BondFutureBasket basket,
            final FutureDeliveryDates deliveryDates) {
        super(name, identifiers);
        this.basket = basket;
        this.deliveryDates = deliveryDates;
    }

    @Override
    public BondFutureBasket underlying() {
        return basket;
    }

    @Override
    public FutureDeliveryDates dates() {
        return deliveryDates;
    }

    public Percentage referenceYield() {
        return basket.referenceYield();
    }

//    @Nonnull
//    public <S extends HasSecurityId, C extends CurrencyId> Optional<S> cheapestToDeliver(final Map<S, Money<C>> bondPrices) {
//        return bondPrices.entrySet()
//                .stream()
//                .filter(e -> basket.contains(e.getKey().securityId()))
//                .min(Comparator.comparing(Map.Entry::getValue))
//                .map(Map.Entry::getKey);
//    }
    @Override
    public <R> R handleWith(final BondDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
