package net.ollie.meerkat.security.interest.option;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.numeric.interest.InterestRateSecurity;
import net.ollie.meerkat.security.derivative.option.AbstractOption;
import net.ollie.meerkat.security.interest.InterestRateDerivative;

/**
 *
 * @author Ollie
 */
public class InterestRateOption
        extends AbstractOption<InterestRateSecurity>
        implements InterestRateDerivative {

    @XmlElementRef(name = "underlying")
    private InterestRateSecurity underlying;

    @Deprecated
    InterestRateOption() {
    }

    @Override
    public InterestRateSecurity underlying() {
        return underlying;
    }

    @Override
    public <R> R handleWith(final InterestRateDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
