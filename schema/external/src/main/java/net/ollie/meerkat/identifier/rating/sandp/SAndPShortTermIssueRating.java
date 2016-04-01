package net.ollie.meerkat.identifier.rating.sandp;

import java.util.Set;

import net.ollie.meerkat.issue.rating.CreditRatingBand;
import net.ollie.meerkat.utils.collections.Sets;

/**
 *
 * @author ollie
 */
public enum SAndPShortTermIssueRating implements SAndPRating {

    A1_PLUS("Extremely strong capacity to meet financial commitments", CreditRatingBand.AAA, CreditRatingBand.AA),
    A1("Strong capacity to meet financial commitments", CreditRatingBand.A),
    A2("Satisfactory capacity to meet financial commitments", CreditRatingBand.A, CreditRatingBand.BBB),
    A3("Weakened capacity to meet financial commitments", CreditRatingBand.BBB),
    B("Uncertain capacity to meet financial commitments", CreditRatingBand.BB, CreditRatingBand.B),
    C("Vulnerable to nonpayment", CreditRatingBand.CCC, CreditRatingBand.CC, CreditRatingBand.C),
    D("In default or in breach of an imputed promise", CreditRatingBand.D);

    private final String description;
    private final Set<CreditRatingBand> bands;

    private SAndPShortTermIssueRating(final String description, final CreditRatingBand... bands) {
        this.description = description;
        this.bands = Sets.asUnmodifiableSet(bands);
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public Set<CreditRatingBand> bands() {
        return bands;
    }

}
