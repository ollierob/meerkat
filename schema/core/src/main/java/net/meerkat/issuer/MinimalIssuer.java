package net.meerkat.issuer;

/**
 *
 * @author ollie
 */
public class MinimalIssuer implements Issuer {

    private final IssuerId id;

    public MinimalIssuer(final IssuerId id) {
        this.id = id;
    }

    @Override
    public IssuerId issuerId() {
        return id;
    }

}
