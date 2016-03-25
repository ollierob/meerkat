package net.ollie.meerkat.security.fx.swap;

import javax.xml.bind.annotation.XmlElement;

import net.ollie.meerkat.security.derivative.swap.AbstractSwap;
import net.ollie.meerkat.security.fx.FxDerivative;
import net.ollie.meerkat.utils.collections.FiniteSequence;
import net.ollie.meerkat.utils.collections.Sequence;

/**
 *
 * @author ollie
 */
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

    public FxSwap(final String name, final FxSwapLeg spot, final FxSwapLeg forward) {
        super(name);
        this.spot = spot;
        this.forward = forward;
    }

    @Override
    public FiniteSequence<FxSwapLeg> legs() {
        return Sequence.of(spot, forward);
    }

    @Override
    public <R> R handleWith(final FxDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
