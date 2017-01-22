package net.meerkat.identifier.rating.moodys;

import java.util.Set;

import javax.xml.bind.annotation.XmlEnum;

import net.meerkat.rating.CreditRating;
import net.meerkat.rating.CreditRatingBand;
import static net.meerkat.rating.CreditRatingBand.*;
import net.ollie.goat.collection.Sets;

/**
 *
 * @author Ollie
 */
@XmlEnum
public enum MoodysGlobalShortTermRating implements MoodysRating {

    P1("Superior ability to repay short-term debt obligations", AAA, AA, A),
    P2("Strong ability to repay short-term debt obligations", A, BBB),
    P3("Acceptable ability to repay short-term debt obligations", BBB),
    NP("Not prime", BB, B, CCC, CC, C),
    NR("Not rated", U);

    private final String description;
    private final Set<CreditRatingBand> bands;

    private MoodysGlobalShortTermRating(final String description, final CreditRatingBand... bands) {
        this.description = description;
        this.bands = Sets.asUnmodifiableSet(bands);
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
