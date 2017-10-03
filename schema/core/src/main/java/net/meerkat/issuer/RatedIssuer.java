package net.meerkat.issuer;

import net.meerkat.rating.CreditRating;
import net.meerkat.rating.HasCreditRating;

/**
 *
 * @author ollie
 */
public class RatedIssuer extends MinimalIssuer implements HasCreditRating {

    private final CreditRating creditRating;

    public RatedIssuer(final IssuerId id, final CreditRating creditRating) {
        super(id);
        this.creditRating = creditRating;
    }

    @Override
    public CreditRating creditRating() {
        return creditRating;
    }

}
