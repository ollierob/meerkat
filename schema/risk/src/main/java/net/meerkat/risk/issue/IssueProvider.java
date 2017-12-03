package net.meerkat.risk.issue;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.risk.issue.exception.IssueException;
import net.meerkat.risk.issue.exception.UnavailableIssueSnapshotException;
import net.ollie.goat.data.CompositeProvider;

/**
 *
 * @author Ollie
 */
public interface IssueProvider<T> extends CompositeProvider<T, InstrumentId, Issue, IssueSnapshot> {

    @Override
    default IssueSnapshot require(final T temporal) throws UnavailableIssueSnapshotException {
        return this.require(temporal, UnavailableIssueSnapshotException::new);
    }

    @Override
    default Issue require(final T temporal, final InstrumentId instrumentId) throws IssueException {
        return CompositeProvider.super.require(temporal, instrumentId);
    }

}
