package net.ollie.meerkat.security.fx.swap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.security.SecurityIds;
import net.ollie.meerkat.security.derivative.swap.AbstractSwap;
import net.ollie.meerkat.security.fx.FxDerivative;
import net.ollie.meerkat.utils.collections.FiniteSequence;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class FxSwap
        extends AbstractSwap
        implements FxDerivative {

    @XmlElement(name = "spot")
    private FxSwapLeg spot;

    @XmlElement(name = "forward")
    private FxSwapLeg forward;

    @Deprecated
    FxSwap() {
    }

    public FxSwap(
            final String name,
            final SecurityIds identifiers,
            final FxSwapLeg spot,
            final FxSwapLeg forward) {
        super(name, identifiers);
        this.spot = spot;
        this.forward = forward;
    }

    @Override
    public FiniteSequence<FxSwapLeg> legs() {
        return FiniteSequence.of(spot, forward);
    }

    @Override
    public <R> R handleWith(final FxDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
