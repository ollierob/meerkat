package net.meerkat.rating;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.meerkat.issuer.HasIssuerId;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author ollie
 */
public interface IssuerCreditRatingProvider extends CreditRatingProvider<IssuerId> {

    @CheckForNull
    default CreditRating get(final HasIssuerId hasIssuerId) {
        return this.get(hasIssuerId.issuerId());
    }

    @Nonnull
    default CreditRating require(final HasIssuerId hasIssuerId) throws UnavailableCreditRatingException {
        return this.require(hasIssuerId.issuerId());
    }

}
