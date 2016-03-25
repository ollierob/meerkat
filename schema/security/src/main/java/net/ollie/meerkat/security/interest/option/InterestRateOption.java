package net.ollie.meerkat.security.interest.option;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.numeric.interest.InterestRate;
import net.ollie.meerkat.security.interest.InterestRateDerivative;
import net.ollie.meerkat.security.derivative.option.AbstractOption;

/**
 *
 * @author Ollie
 */
public class InterestRateOption
        extends AbstractOption<InterestRate>
        implements InterestRateDerivative {

    @XmlElementRef(name = "underlying")
    private InterestRate underlying;

    @Deprecated
    InterestRateOption() {
    }

    @Override
    public InterestRate underlying() {
        return underlying;
    }

    @Override
    public <R> R handleWith(final InterestRateDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
