package net.meerkat.instrument.equity;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.issue.Issue;

/**
 *
 * @author ollie
 */
public class PreferredStock extends IssuedSecurity implements Stock {

    public PreferredStock(
            final String name,
            final InstrumentIds identifiers,
            final Issue issue) {
        super(name, identifiers, issue);
    }

    @Override
    public <R> R handleWith(final Handler<R> handler) {
        return handler.handle(this);
    }

}
