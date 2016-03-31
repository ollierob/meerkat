package net.ollie.meerkat.identifier.rating.moodys;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlEnum;

import net.ollie.meerkat.issue.rating.CreditRating;
import net.ollie.meerkat.issue.rating.CreditRatingBand;
import static net.ollie.meerkat.issue.rating.CreditRatingBand.*;

/**
 *
 * @author Ollie
 */
@XmlEnum
public enum MoodysShortTermRating implements MoodysRating {

    P1(AAA, AA, A),
    P2(A, BBB),
    P3(BBB),
    NP(BB, B, CCC, CC, C, D, U); //Not prime

    private final Set<CreditRatingBand> bands;

    private MoodysShortTermRating(final CreditRatingBand... bands) {
        this.bands = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(bands)));
    }

    @Override
    public boolean isInvestmentGrade() {
        return this != NP;
    }

    @Override
    public Set<CreditRatingBand> bands() {
        return bands;
    }

    @Override
    public int compareTo(final CreditRating that) {
        return that instanceof MoodysShortTermRating
                ? this.compareTo((MoodysShortTermRating) that)
                : MoodysRating.super.compareTo(that);
    }

}
