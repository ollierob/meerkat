package net.meerkat.instrument;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author ollie
 */
public class IssuedSecurity extends NamedInstrument implements Security {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "issuer")
    private IssuerId issuer;

    @Deprecated
    protected IssuedSecurity() {
    }

    public IssuedSecurity(final String name, final InstrumentIds identifiers, final IssuerId issuer) {
        super(name, identifiers);
        this.issuer = issuer;
    }

    @Override
    public IssuerId issuerId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
