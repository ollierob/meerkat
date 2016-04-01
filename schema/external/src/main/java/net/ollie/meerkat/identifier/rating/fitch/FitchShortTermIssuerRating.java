package net.ollie.meerkat.identifier.rating.fitch;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.ollie.meerkat.issue.rating.CreditRating;
import net.ollie.meerkat.issue.rating.CreditRatingBand;

/**
 *
 * @author ollie
 */
public enum FitchShortTermIssuerRating implements FitchIssuerRating {

    F1_PLUS(CreditRatingBand.AAA, CreditRatingBand.AA),
    F1(CreditRatingBand.A),
    F2(CreditRatingBand.A, CreditRatingBand.BBB),
    F3(CreditRatingBand.BBB),
    B(CreditRatingBand.BB, CreditRatingBand.B),
    C(CreditRatingBand.CCC),
    RD(CreditRatingBand.D),
    D(CreditRatingBand.D);

    private final Set<CreditRatingBand> bands;

    private FitchShortTermIssuerRating(final CreditRatingBand... bands) {
        this.bands = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(bands)));
    }

    @Override
    public Set<CreditRatingBand> bands() {
        return bands;
    }

    @Override
    public int compareTo(final CreditRating that) {
        return that instanceof FitchShortTermIssuerRating
                ? this.compareTo((FitchShortTermIssuerRating) that)
                : FitchIssuerRating.super.compareTo(that);
    }

}
