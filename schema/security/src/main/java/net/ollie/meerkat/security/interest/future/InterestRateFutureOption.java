package net.ollie.meerkat.security.interest.future;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.security.derivative.option.AbstractOption;
import net.ollie.meerkat.security.interest.InterestRateDerivative;

/**
 *
 * @author ollie
 */
public class InterestRateFutureOption
        extends AbstractOption<InterestRateFuture>
        implements InterestRateDerivative {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "underlying")
    private InterestRateFuture underlying;
    
    @Deprecated
    InterestRateFutureOption() {
    }

    @Override
    public InterestRateFuture underlying() {
        return underlying;
    }

    @Override
    public <R> R handleWith(final InterestRateDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
