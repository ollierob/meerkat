package net.ollie.meerkat.security.interest.option;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.security.interest.InterestRateDerivative;
import net.ollie.meerkat.security.interest.swap.InterestRateSwap;
import net.ollie.meerkat.security.derivative.option.AbstractOption;

/**
 *
 * @author Ollie
 */
public class Swaption
        extends AbstractOption<InterestRateSwap>
        implements InterestRateDerivative {

    @XmlElementRef(name = "underlying")
    private InterestRateSwap underlying;

    @Deprecated
    Swaption() {
    }

    @Override
    public InterestRateSwap underlying() {
        return underlying;
    }

    @Override
    public <R> R handleWith(final InterestRateDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
