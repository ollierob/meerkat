package net.meerkat.issuer;

import net.meerkat.rating.CreditRating;
import net.meerkat.utils.HasName;

/**
 *
 * @author ollie
 * @see CreditRating
 */
public interface Issuer extends HasName, HasIssuerId {

    static Issuer of(final IssuerId id, final String name) {
        return new IdentifiedIssuer(id, name);
    }

}
