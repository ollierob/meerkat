package net.meerkat.issuer;

/**
 *
 * @author ollie
 */
public interface Issuer extends HasIssuerId {

    static Issuer of(final IssuerId id) {
        return new MinimalIssuer(id);
    }

}
