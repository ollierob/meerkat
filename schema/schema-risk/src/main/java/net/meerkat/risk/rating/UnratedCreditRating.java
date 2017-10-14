package net.meerkat.risk.rating;

import java.util.Collections;
import java.util.Set;

/**
 *
 * @author ollie
 */
class UnratedCreditRating implements CreditRating {

    @Override
    public String description() {
        return "Unrated";
    }

    @Override
    public Set<CreditRatingBand> bands() {
        return Collections.singleton(CreditRatingBand.U);
    }

    @Override
    public String agency() {
        return null;
    }

}
