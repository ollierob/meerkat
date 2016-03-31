package net.ollie.meerkat.identifier.rating.fitch;

import net.ollie.meerkat.issue.rating.CreditRating;

/**
 *
 * @author ollie
 */
public interface FitchIssuerRating extends CreditRating {

    @Override
    default String agency() {
        return "Fitch";
    }

}
