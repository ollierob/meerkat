package net.meerkat.risk.rating;

import java.time.LocalDate;

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
    default CreditRating get(final LocalDate date, final HasIssuerId hasIssuerId) {
        return this.get(date, hasIssuerId.issuerId());
    }

    @Nonnull
    default CreditRating require(final LocalDate date, final HasIssuerId hasIssuerId) throws UnavailableCreditRatingException {
        return this.require(date, hasIssuerId.issuerId());
    }

}
