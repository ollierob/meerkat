package net.meerkat.risk.issuer;

import net.meerkat.issuer.IssuerId;
import net.meerkat.issuer.MinimalIssuer;
import net.meerkat.risk.issue.RatedIssue;
import net.meerkat.risk.rating.CreditRating;
import net.meerkat.risk.rating.HasCreditRating;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 *
 * @author ollie
 * @see RatedIssue
 */
public class RatedIssuer extends MinimalIssuer implements HasCreditRating {

    private final CreditRating creditRating;

    public RatedIssuer(@Nonnull final IssuerId id, @Nonnull final CreditRating creditRating) {
        super(id);
        this.creditRating = Objects.requireNonNull(creditRating);
    }

    @Override
    public CreditRating creditRating() {
        return creditRating;
    }

}
