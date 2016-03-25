package net.ollie.meerkat.security.bond.swap;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.security.bond.Bond;
import net.ollie.meerkat.security.bond.BondDerivative;
import net.ollie.meerkat.security.derivative.swap.AbstractSwap;
import net.ollie.meerkat.utils.collections.Sequence;

/**
 *
 * @author Ollie
 */
public class AssetSwap
        extends AbstractSwap
        implements BondDerivative<Bond> {

    @XmlElementRef(name = "underlying")
    private Bond underlying;

    @Deprecated
    AssetSwap() {
    }

    @Override
    public Bond underlying() {
        return underlying;
    }

    @Override
    public Sequence<AssetSwapLeg> legs() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public <R> R handleWith(final BondDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
