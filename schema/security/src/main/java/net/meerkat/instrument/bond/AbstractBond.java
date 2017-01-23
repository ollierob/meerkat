package net.meerkat.instrument.bond;

import java.util.Optional;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;

import net.meerkat.money.Money;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.instrument.bond.call.BondCall;

/**
 *
 * @author Ollie
 */
@XmlSeeAlso({ConvertibleBond.class, StraightBond.class, PerpetualBond.class})
public abstract class AbstractBond
        extends NamedInstrument
        implements Bond {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "par")
    private Money<?> par;

    @XmlElementRef(name = "call")
    private BondCall call;

    @Deprecated
    protected AbstractBond() {
    }

    protected AbstractBond(final String name, final InstrumentIds identifiers, final Money<?> par, final BondCall call) {
        super(name, identifiers);
        this.par = par;
        this.call = call;
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
