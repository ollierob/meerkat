package net.meerkat.risk.issuer;

import net.meerkat.issuer.IssuerId;
import net.meerkat.risk.issuer.exception.UnknownIssuerException;
import net.ollie.goat.data.Provider;

/**
 *
 * @author Ollie
 */
public interface IssuerSnapshot extends Provider<IssuerId, Issuer> {

    @Override
    default Issuer require(final IssuerId issuerId) throws UnknownIssuerException {
        return this.require(issuerId, UnknownIssuerException::new);
    }

}
