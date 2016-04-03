package net.ollie.meerkat.security.bond.future;

import java.time.Month;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;

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

    @XmlElement(name = "basket")
    private BondFutureBasket basket;

    @XmlElementRef(name = "delivery")
    private FutureDelivery<Month> deliveryMonths;

    @Deprecated
    BondFuture() {
    }

    @Override
    public BondFutureBasket underlying() {
        return basket;
    }

    @Override
    public FutureDelivery<Month> deliveryDates() {
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
