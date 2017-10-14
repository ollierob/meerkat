package net.meerkat.risk.issue;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.risk.issue.exception.UnknownIssueException;
import net.ollie.goat.data.Provider;

/**
 *
 * @author ollie
 */
public interface Issues extends Provider<InstrumentId, Issue> {

    @Override
    default Issue require(final InstrumentId id) throws UnknownIssueException {
        return this.require(id, UnknownIssueException::new);
    }

}
