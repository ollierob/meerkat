package net.ollie.meerkat.identifier.rating.fitch;

import net.ollie.meerkat.issue.rating.CreditRating;

/**
 *
 * @author ollie
 * @see
 * <a href="https://www.fitchratings.com/jsp/general/RatingsDefinitions.faces?context=5&detail=507&context_ln=5&detail_ln=500#LTR">Fitch
 * ratings</a>
 */
public interface FitchIssuerRating extends CreditRating {

    @Override
    default String agency() {
        return "Fitch";
    }

}
