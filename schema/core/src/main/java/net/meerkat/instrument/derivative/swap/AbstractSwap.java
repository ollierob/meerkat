package net.meerkat.instrument.derivative.swap;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.issue.Issue;

/**
 *
 * @author Ollie
 */
public abstract class AbstractSwap
        extends IssuedSecurity
        implements Swap {

    protected AbstractSwap(final String name, final InstrumentIds identifiers, final Issue issue) {
        super(name, identifiers, issue);
    }

}
