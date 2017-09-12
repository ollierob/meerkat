package net.meerkat.issuer;

import net.meerkat.Explainable;
import net.meerkat.Named;
import net.meerkat.rating.CreditRating;

/**
 *
 * @author ollie
 * @see CreditRating
 */
public class IdentifiedIssuer
        extends Named
        implements Issuer, Explainable {

    private final IssuerId id;

    public IdentifiedIssuer(final IssuerId id, final String name) {
        super(name);
        this.id = id;
    }

    @Override
    public IssuerId issuerId() {
        return id;
    }

    @Override
    public ExplanationBuilder explain() {
        return this.explanationBuilder()
                .put("id", id);
    }

}
