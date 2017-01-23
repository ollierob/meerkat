package net.meerkat.instrument.derivative.forward;

import javax.xml.bind.annotation.XmlTransient;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.security.Instrument;

/**
 *
 * @author Ollie
 */
@XmlTransient
public abstract class AbstractFuture<S extends Instrument>
        extends NamedInstrument
        implements Future<S> {

    private static final long serialVersionUID = 1L;

    @Deprecated
    protected AbstractFuture() {
    }

    public AbstractFuture(final String name, final InstrumentIds identifiers) {
        super(name, identifiers);
    }

}
