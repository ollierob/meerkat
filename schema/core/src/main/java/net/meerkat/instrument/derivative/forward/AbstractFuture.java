package net.meerkat.instrument.derivative.forward;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.issue.Issue;

/**
 *
 * @author Ollie
 */
public abstract class AbstractFuture<U extends Instrument>
        extends IssuedSecurity
        implements Future<U> {

    private static final long serialVersionUID = 1L;

    protected AbstractFuture(final String name, final InstrumentIds identifiers, final Issue issue) {
        super(name, identifiers, issue);
    }

}
