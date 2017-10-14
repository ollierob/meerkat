package net.meerkat.issuer;

import java.util.Objects;

import javax.annotation.Nonnull;

import net.meerkat.rating.CreditRating;
import net.meerkat.rating.HasCreditRating;

/**
 *
 * @author ollie
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
