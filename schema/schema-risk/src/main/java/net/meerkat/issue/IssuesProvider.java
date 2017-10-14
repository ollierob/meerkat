package net.meerkat.issue;

import javax.annotation.Nonnull;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.issue.exception.IssueException;
import net.meerkat.issue.exception.UnavailableIssueException;
import net.ollie.goat.data.Provider;

/**
 *
 * @author Ollie
 */
public interface IssuesProvider<T> extends Provider<T, Issues> {

    @Override
    default Issues require(final T temporal) throws UnavailableIssueException {
        return this.require(temporal, UnavailableIssueException::new);
    }

    @Nonnull
    default Issue require(final T temporal, final InstrumentId instrumentId) throws IssueException {
        return this.require(temporal).require(instrumentId);
    }

}
