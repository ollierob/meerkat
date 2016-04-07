package net.ollie.meerkat.security.bond.future;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.identifier.security.SecurityIds;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.security.bond.BondDerivative;
import net.ollie.meerkat.security.derivative.forward.AbstractFuture;
import net.ollie.meerkat.security.derivative.forward.FutureDelivery;

/**
 *
 * @author ollie
 */
public class BondFuture
        extends AbstractFuture<BondFutureBasket>
        implements BondDerivative<BondFutureBasket> {

    @XmlElement(name = "basket", required = true)
    private BondFutureBasket basket;

    @XmlElementRef(name = "delivery", required = true)
    private FutureDelivery deliveryMonths;

    @Deprecated
    BondFuture() {
    }

    public BondFuture(
            final String name,
            final SecurityIds identifiers,
            final BondFutureBasket basket,
            final FutureDelivery deliveryMonths) {
        super(name, identifiers);
        this.basket = basket;
        this.deliveryMonths = deliveryMonths;
    }

    @Override
    public BondFutureBasket underlying() {
        return basket;
    }

    @Override
    public FutureDelivery deliveryDates() {
        return deliveryMonths;
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
