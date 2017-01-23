package net.meerkat.instrument.derivative.forward;

import javax.xml.bind.annotation.XmlTransient;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.issuer.IssuerId;
import net.meerkat.security.Instrument;

/**
 *
 * @author Ollie
 */
@XmlTransient
public abstract class AbstractFuture<S extends Instrument>
        extends IssuedSecurity
        implements Future<S> {

    private static final long serialVersionUID = 1L;

    @Deprecated
    protected AbstractFuture() {
    }

    public AbstractFuture(final String name, final InstrumentIds identifiers, final IssuerId issuer) {
        super(name, identifiers, issuer);
    }

}
