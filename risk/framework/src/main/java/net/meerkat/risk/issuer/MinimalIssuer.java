package net.meerkat.risk.issuer;

import net.meerkat.issuer.IssuerId;

import java.util.Objects;

/**
 *
 * @author ollie
 */
public class MinimalIssuer implements Issuer {

    private final IssuerId id;

    public MinimalIssuer(final IssuerId id) {
        this.id = Objects.requireNonNull(id);
    }

    @Override
    public IssuerId issuerId() {
        return id;
    }

}
