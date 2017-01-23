package net.meerkat.instrument.fx.swap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.derivative.swap.AbstractSwap;
import net.meerkat.instrument.fx.FxDerivative;
import net.ollie.meerkat.utils.collections.sequence.FiniteSequence;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class FxSwap
        extends AbstractSwap
        implements FxDerivative {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "near")
    private FxSwapLeg near;

    @XmlElement(name = "far")
    private FxSwapLeg far;

    @Deprecated
    FxSwap() {
    }

    public FxSwap(
            final String name,
            final InstrumentIds identifiers,
            final FxSwapLeg near,
            final FxSwapLeg far) {
        super(name, identifiers);
        this.near = near;
        this.far = far;
    }

    @Override
    public FiniteSequence<FxSwapLeg> legs() {
        return FiniteSequence.of(near, far);
    }

    @Override
    public <R> R handleWith(final FxDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
