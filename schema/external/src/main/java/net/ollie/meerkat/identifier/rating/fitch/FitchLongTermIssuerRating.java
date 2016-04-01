package net.ollie.meerkat.identifier.rating.fitch;

import java.util.Collections;
import java.util.Set;

import net.ollie.meerkat.issue.rating.CreditRating;
import net.ollie.meerkat.issue.rating.CreditRatingBand;

/**
 *
 * @author ollie
 */
public enum FitchLongTermIssuerRating implements FitchIssuerRating {

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
    CCC(CreditRatingBand.CCC),
    CC(CreditRatingBand.CC),
    C(CreditRatingBand.C),
    RD(CreditRatingBand.D),
    D(CreditRatingBand.D),
    NR(CreditRatingBand.U);

    private final CreditRatingBand band;

    private FitchLongTermIssuerRating(CreditRatingBand band) {
        this.band = band;
    }

    @Override
    public Set<CreditRatingBand> bands() {
        return Collections.singleton(band);
    }

    @Override
    public CreditRatingBand bestBand() {
        return band;
    }

    @Override
    public boolean isDefault() {
        return this == D;
    }

    @Override
    public int compareTo(final CreditRating that) {
        return that instanceof FitchLongTermIssuerRating
                ? this.compareTo((FitchLongTermIssuerRating) that)
                : FitchIssuerRating.super.compareTo(that);
    }

}
