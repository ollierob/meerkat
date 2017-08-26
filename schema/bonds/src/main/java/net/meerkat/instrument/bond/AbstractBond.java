package net.meerkat.instrument.bond;

import java.util.Optional;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.instrument.bond.call.BondCall;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
public abstract class AbstractBond
        extends NamedInstrument
        implements Bond {

    private static final long serialVersionUID = 1L;

    private final Money<?> par;
    private final BondCall call;
    private final IssuerId issuer;

    protected AbstractBond(
            final String name,
            final InstrumentIds identifiers,
            final Money<?> par,
            final BondCall call,
            final IssuerId issuer) {
        super(name, identifiers);
        this.par = par;
        this.call = call;
        this.issuer = issuer;
    }

    @Override
    public Money<?> par() {
        return par;
    }

    @Override
    public Optional<BondCall> call() {
        return Optional.ofNullable(call);
    }

    @Override
    public IssuerId issuerId() {
        return issuer;
    }

}
