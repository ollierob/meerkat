package net.ollie.meerkat.security.bond;

import java.util.Optional;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.NamedSecurity;
import net.ollie.meerkat.security.bond.call.BondCall;

/**
 *
 * @author Ollie
 */
public abstract class AbstractBond
        extends NamedSecurity
        implements Bond {

    @XmlElementRef(name = "par")
    private Money par;

    @XmlElementRef(name = "call")
    private BondCall call;

    @Deprecated
    protected AbstractBond() {
    }

    protected AbstractBond(final String name, final Money par, final BondCall call) {
        super(name);
        this.par = par;
        this.call = call;
    }

    protected Money par() {
        return par;
    }

    @Override
    public Optional<BondCall> call() {
        return Optional.ofNullable(call);
    }

}
