package net.ollie.meerkat.identifier.rating.fitch;

import java.util.Collections;
import java.util.Set;

import net.ollie.meerkat.issue.rating.CreditRating;
import net.ollie.meerkat.issue.rating.CreditRatingBand;

/**
 *
 * @author ollie
 */
public enum FitchLongTermRating implements FitchIssuerRating {

    AAA("", FitchShortTermRating.F1_PLUS, CreditRatingBand.AAA),
    AA_PLUS("", FitchShortTermRating.F1_PLUS, CreditRatingBand.AA),
    AA("", FitchShortTermRating.F1_PLUS, CreditRatingBand.AA),
    AA_MINUS("", FitchShortTermRating.F1_PLUS, CreditRatingBand.AA),
    A_PLUS("", FitchShortTermRating.F1_PLUS, CreditRatingBand.A),
    A("", FitchShortTermRating.F1, CreditRatingBand.A),
    A_MINUS("", FitchShortTermRating.F1, CreditRatingBand.A),
    BBB_PLUS("", FitchShortTermRating.F2, CreditRatingBand.BBB),
    BBB("", FitchShortTermRating.F2, CreditRatingBand.BBB),
    BBB_MINUS("", FitchShortTermRating.F3, CreditRatingBand.BBB),
    BB_PLUS("", FitchShortTermRating.B, CreditRatingBand.BB),
    BB("", FitchShortTermRating.B, CreditRatingBand.BB),
    BB_MINUS("", FitchShortTermRating.B, CreditRatingBand.BB),
    B_PLUS("", FitchShortTermRating.B, CreditRatingBand.B),
    B("", FitchShortTermRating.B, CreditRatingBand.B),
    B_MINUS("", FitchShortTermRating.B, CreditRatingBand.B),
    CCC("", FitchShortTermRating.C, CreditRatingBand.CCC),
    CC("", FitchShortTermRating.C, CreditRatingBand.CC),
    C("", FitchShortTermRating.C, CreditRatingBand.C),
    RD("", FitchShortTermRating.RD, CreditRatingBand.D),
    D("", FitchShortTermRating.D, CreditRatingBand.D),
    NR("", FitchShortTermRating.NR, CreditRatingBand.U);

    private final String description;
    private final FitchShortTermRating shortTerm;
    private final CreditRatingBand band;

    private FitchLongTermRating(String description, FitchShortTermRating shortTerm, CreditRatingBand band) {
        this.description = description;
        this.shortTerm = shortTerm;
        this.band = band;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public FitchShortTermRating shortTermEquivalent() {
        return shortTerm;
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
        return that instanceof FitchLongTermRating
                ? this.compareTo((FitchLongTermRating) that)
                : FitchIssuerRating.super.compareTo(that);
    }

}
