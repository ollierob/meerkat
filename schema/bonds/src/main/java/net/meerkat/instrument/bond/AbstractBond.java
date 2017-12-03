package net.meerkat.instrument.bond;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.instrument.bond.call.BondCall;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;

import java.util.Optional;

/**
 *
 * @author Ollie
 */
public abstract class AbstractBond
        extends NamedInstrument
        implements Bond {

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
