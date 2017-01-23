package net.meerkat.instrument.interest.option;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.numeric.interest.InterestRateSecurity;
import net.meerkat.instrument.derivative.option.AbstractOption;
import net.meerkat.instrument.interest.InterestRateDerivative;

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
