package net.ollie.meerkat.identifier.rating.sandp;

import java.util.Collections;
import java.util.Set;

import net.ollie.meerkat.issue.rating.CreditRating;
import net.ollie.meerkat.issue.rating.CreditRatingBand;

/**
 *
 * @author ollie
 */
public enum SAndPLongTermIssueRating implements SAndPRating {

    AAA(CreditRatingBand.AAA),
    AA_PLUS(CreditRatingBand.AA),
    AA(CreditRatingBand.AA),
    AA_MINUS(CreditRatingBand.AA),
    A_PLUS(CreditRatingBand.A),
    A(CreditRatingBand.A),
    A_MINUS(CreditRatingBand.A),
    BBB_PLUS(CreditRatingBand.BBB),
    BBB(CreditRatingBand.BBB),
    BBB_MINUS(CreditRatingBand.BBB),
    BB_PLUS(CreditRatingBand.BB),
    BB(CreditRatingBand.BB),
    BB_MINUS(CreditRatingBand.BB),
    B_PLUS(CreditRatingBand.B),
    B(CreditRatingBand.B),
    B_MINUS(CreditRatingBand.B),
    CCC_PLUS(CreditRatingBand.CCC),
    CCC(CreditRatingBand.CCC),
    CCC_MINUS(CreditRatingBand.CCC),
    CC(CreditRatingBand.CC),
    C_PLUS(CreditRatingBand.C),
    C(CreditRatingBand.C),
    C_MINUS(CreditRatingBand.C),
    RD(CreditRatingBand.D),
    SD(CreditRatingBand.D),
    D(CreditRatingBand.D),
    NR(CreditRatingBand.U);

    private final CreditRatingBand band;

    private SAndPLongTermIssueRating(final CreditRatingBand band) {
        this.band = band;
    }

    @Override
    public CreditRatingBand bestBand() {
        return band;
    }

    @Override
    public Set<CreditRatingBand> bands() {
        return Collections.singleton(band);
    }

    @Override
    public int compareTo(final CreditRating that) {
        return that instanceof SAndPLongTermIssueRating
                ? this.compareTo((SAndPLongTermIssueRating) that)
                : SAndPRating.super.compareTo(that);
    }

}
