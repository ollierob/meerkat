package net.meerkat.risk.issuer;

import net.meerkat.issuer.IssuerId;
import net.meerkat.risk.issuer.exception.IssuerException;
import net.meerkat.risk.issuer.exception.UnavailableIssuerSnapshotException;
import net.ollie.goat.data.CompositeProvider;

/**
 *
 * @author Ollie
 */
public interface IssuerProvider<T> extends CompositeProvider<T, IssuerId, Issuer, IssuerSnapshot> {

    @Override
    default IssuerSnapshot require(final T temporal) throws UnavailableIssuerSnapshotException {
        return this.require(temporal, UnavailableIssuerSnapshotException::new);
    }

    @Override
    default Issuer require(final T temporal, final IssuerId issuerId) throws IssuerException {
        return CompositeProvider.super.require(temporal, issuerId);
    }

}
