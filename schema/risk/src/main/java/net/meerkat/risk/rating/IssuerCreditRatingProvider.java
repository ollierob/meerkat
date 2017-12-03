package net.meerkat.risk.rating;

import net.meerkat.issuer.HasIssuerId;
import net.meerkat.issuer.IssuerId;
import net.meerkat.risk.rating.exception.UnavailableCreditRatingException;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface IssuerCreditRatingProvider<T> extends CreditRatingProvider<T, IssuerId> {

    @CheckForNull
    default CreditRating get(final T temporal, final HasIssuerId hasIssuerId) {
        return this.get(temporal, hasIssuerId.issuerId());
    }

    @Nonnull
    default CreditRating require(final T temporal, final HasIssuerId hasIssuerId) throws UnavailableCreditRatingException {
        return this.require(temporal, hasIssuerId.issuerId());
    }

}
