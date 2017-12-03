package net.meerkat.instrument;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.issuer.HasIssuerId;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author ollie
 */
public class IssuedSecurity
        extends NamedInstrument
        implements Security, HasIssuerId {

    private final IssuerId issuerId;

    public IssuedSecurity(final String name, final InstrumentIds identifiers, final IssuerId issuerId) {
        super(name, identifiers);
        this.issuerId = issuerId;
    }

    @Override
    public IssuerId issuerId() {
        return issuerId;
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain().put("issue", issuerId);
    }

}
