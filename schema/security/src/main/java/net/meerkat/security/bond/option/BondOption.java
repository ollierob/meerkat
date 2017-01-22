package net.meerkat.security.bond.option;

import net.meerkat.security.NamedSecurity;
import net.meerkat.security.bond.Bond;
import net.meerkat.security.bond.BondDerivative;

/**
 *
 * @author Ollie
 */
public class BondOption
        extends NamedSecurity
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
