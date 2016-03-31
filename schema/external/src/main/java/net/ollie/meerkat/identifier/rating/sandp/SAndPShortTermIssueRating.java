package net.ollie.meerkat.identifier.rating.sandp;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.ollie.meerkat.issue.rating.CreditRatingBand;

/**
 *
 * @author ollie
 */
public enum SAndPShortTermIssueRating implements SAndPRating {

    A1_PLUS(CreditRatingBand.AAA, CreditRatingBand.AA),
    A1(CreditRatingBand.A),
    A2(CreditRatingBand.A, CreditRatingBand.BBB),
    A3(CreditRatingBand.BBB),
    B(CreditRatingBand.BB, CreditRatingBand.B),
    C(CreditRatingBand.CCC, CreditRatingBand.CC, CreditRatingBand.C),
    D(CreditRatingBand.D);

    private final Set<CreditRatingBand> bands;

    private SAndPShortTermIssueRating(final CreditRatingBand... bands) {
        this.bands = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(bands)));
    }

    @Override
    public Set<CreditRatingBand> bands() {
        return bands;
    }

}
