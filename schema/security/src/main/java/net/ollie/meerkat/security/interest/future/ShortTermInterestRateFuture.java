package net.ollie.meerkat.security.interest.future;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.numeric.interest.InterestRate;
import net.ollie.meerkat.security.derivative.forward.AbstractFuture;
import net.ollie.meerkat.security.derivative.forward.FutureDelivery;
import net.ollie.meerkat.security.interest.InterestRateDerivative;

/**
 *
 * @author Ollie
 */
public class ShortTermInterestRateFuture
        extends AbstractFuture<InterestRate>
        implements InterestRateDerivative {

    @XmlElementRef(name = "delivery")
    private FutureDelivery delivery;

    @Deprecated
    ShortTermInterestRateFuture() {
    }

    @Override
    public FutureDelivery deliveryDates() {
        return delivery;
    }

    @Override
    public <R> R handleWith(final InterestRateDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
