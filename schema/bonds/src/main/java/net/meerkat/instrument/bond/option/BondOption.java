package net.meerkat.instrument.bond.option;

import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.instrument.bond.BondDerivative;

/**
 *
 * @author Ollie
 */
public class BondOption
        extends IssuedSecurity
        implements BondDerivative<Bond> {

    @Deprecated
    BondOption() {
    }

    @Override
    public Bond underlying() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public <R> R handleWith(final BondDerivative.Handler<R> handler) {
        throw new UnsupportedOperationException(); //TODO
    }

}
