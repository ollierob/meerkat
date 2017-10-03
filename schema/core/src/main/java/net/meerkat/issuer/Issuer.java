package net.meerkat.issuer;

import net.meerkat.rating.CreditRating;

/**
 *
 * @author ollie
 * @see CreditRating
 */
public interface Issuer extends HasIssuerId {

    static Issuer of(final IssuerId id) {
        return new MinimalIssuer(id);
    }

}
