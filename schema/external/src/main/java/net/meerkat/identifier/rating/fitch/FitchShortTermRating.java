package net.meerkat.identifier.rating.fitch;

import java.util.Set;

import net.ollie.goat.collection.Sets;
import net.meerkat.risk.rating.CreditRating;
import net.meerkat.risk.rating.CreditRatingBand;

/**
 *
 * @author ollie
 */
public enum FitchShortTermRating implements FitchIssuerRating {

    F1_PLUS("Highest short-term credit quality", CreditRatingBand.AAA, CreditRatingBand.AA),
    F1("Highest short-term credit quality", CreditRatingBand.A),
    F2("Good short-term credit quality", CreditRatingBand.A, CreditRatingBand.BBB),
    F3("Fair short-term credit quality", CreditRatingBand.BBB),
    B("Speculative short-term credit quality", CreditRatingBand.BB, CreditRatingBand.B),
    C("High short-term default risk", CreditRatingBand.CCC),
    RD("Restricted default", CreditRatingBand.D),
    D("Default", CreditRatingBand.D),
    NR("Not rated", CreditRatingBand.U);

    private final String description;
    private final Set<CreditRatingBand> bands;

    private FitchShortTermRating(final String description, final CreditRatingBand... bands) {
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

    @Override
    public int compareTo(final CreditRating that) {
        return that instanceof FitchShortTermRating
                ? this.compareTo((FitchShortTermRating) that)
                : FitchIssuerRating.super.compareTo(that);
    }

    @Override
    public FitchShortTermRating shortTermEquivalent() {
        return this;
    }

}
