package net.meerkat.risk.issuer;

import net.meerkat.issuer.HasIssuerId;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author ollie
 */
public interface Issuer extends HasIssuerId {

    static Issuer of(final IssuerId id) {
        return new MinimalIssuer(id);
    }

}
