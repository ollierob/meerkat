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
public enum MoodysGlobalShortTermRating implements MoodysRating {

    P1("Superior ability to repay short-term debt obligations", AAA, AA, A),
    P2("Strong ability to repay short-term debt obligations", A, BBB),
    P3("Acceptable abiluty to repay short-term debt obligations", BBB),
    NP("Not prime", BB, B, CCC, CC, C, D, U);

    private final String description;
    private final Set<CreditRatingBand> bands;

    private MoodysGlobalShortTermRating(final String description, final CreditRatingBand... bands) {
        this.description = description;
        this.bands = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(bands)));
    }

    @Override
    public String description() {
        return description;
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
        return that instanceof MoodysGlobalShortTermRating
                ? this.compareTo((MoodysGlobalShortTermRating) that)
                : MoodysRating.super.compareTo(that);
    }

}
