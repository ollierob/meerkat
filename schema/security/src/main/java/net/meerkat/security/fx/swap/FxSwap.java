package net.meerkat.security.fx.swap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.security.SecurityIds;
import net.meerkat.security.derivative.swap.AbstractSwap;
import net.meerkat.security.fx.FxDerivative;
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
            final SecurityIds identifiers,
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
