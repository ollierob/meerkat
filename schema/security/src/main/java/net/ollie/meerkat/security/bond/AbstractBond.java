package net.ollie.meerkat.security.bond;

import java.util.Optional;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.identifier.security.Isin;
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

    @XmlElement(name = "isin")
    private Isin isin;

    @XmlElementRef(name = "par")
    private Money<?> par;

    @XmlElementRef(name = "call")
    private BondCall call;

    @Deprecated
    protected AbstractBond() {
    }

    protected AbstractBond(
            final String name,
            final Isin isin,
            final Money<?> par,
            final BondCall call) {
        super(name);
        this.isin = isin;
        this.par = par;
        this.call = call;
    }

    @Override
    public Isin isin() {
        return isin;
    }

    @Override
    public Money<?> par() {
        return par;
    }

    @Override
    public Optional<BondCall> call() {
        return Optional.ofNullable(call);
    }

}
