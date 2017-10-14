package net.meerkat.identifier.rating.bloomberg;

import java.util.Set;

import net.meerkat.risk.rating.CreditRating;
import net.meerkat.risk.rating.CreditRatings;

/**
 *
 * @author Ollie
 */
public class BloombergCompositeRating extends CreditRatings {

    public BloombergCompositeRating(final CreditRating finalRating, final Set<CreditRating> otherRatings) {
        super(finalRating, otherRatings);
    }

    @Override
    public String agency() {
        return "Bloomberg";
    }

}
