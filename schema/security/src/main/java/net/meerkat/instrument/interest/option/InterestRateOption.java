package net.meerkat.instrument.interest.option;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.instrument.derivative.option.AbstractOption;
import net.meerkat.instrument.interest.InterestRateDerivative;
import net.meerkat.numeric.interest.InterestRateSecurity;

/**
 *
 * @author Ollie
 */
public class InterestRateOption
        extends AbstractOption<InterestRateSecurity>
        implements InterestRateDerivative {

    private static final long serialVersionUID = 1L;

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

    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("underlying", underlying);
    }

}
