package net.ollie.meerkat.security.bond.option;

import net.ollie.meerkat.security.NamedSecurity;
import net.ollie.meerkat.security.bond.Bond;
import net.ollie.meerkat.security.bond.BondDerivative;

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
