package net.meerkat.identifier.rating.moodys;

import java.util.Collections;
import java.util.Set;

import javax.xml.bind.annotation.XmlEnum;

import static net.meerkat.identifier.rating.moodys.MoodysGlobalShortTermRating.*;
import net.meerkat.rating.CreditRating;
import net.meerkat.rating.CreditRatingBand;
import static net.meerkat.rating.CreditRatingBand.*;

/**
 *
 * @author Ollie
 */
@XmlEnum
public enum MoodysLongTermRating implements MoodysRating {

    Aaa("Highest quality, lowest level of credit risk", P1, AAA),
    Aa1("High quality, very low credit risk", P1, AA),
    Aa2("High quality, very low credit risk", P1, AA),
    Aa3("High quality, very low credit risk", P1, AA),
    A1("Upper-medium grade, low credit risk", P1, A),
    A2("Upper-medium grade, low credit risk", P1, A),
    A3("Upper-medium grade, low credit risk", P1, A),
    Baa1("Medium grade, moderate credit risk", P2, BBB),
    Baa2("Medium grade, moderate credit risk", P2, BBB),
    Baa3("Medium grade, moderate credit risk", P3, BBB),
    Ba1("Speculative, substantial credit risk", NP, BB),
    Ba2("Speculative, substantial credit risk", NP, BB),
    Ba3("Speculative, substantial credit risk", NP, BB),
    B1("Speculative, high credit risk", NP, B),
    B2("Speculative, high credit risk", NP, B),
    B3("Speculative, high credit risk", NP, B),
    Caa1("Speculative, very high credit risk", NP, CreditRatingBand.CCC),
    Caa2("Speculative, very high credit risk", NP, CreditRatingBand.CCC),
    Caa3("Speculative, very high credit risk ", NP, CreditRatingBand.CC),
    Ca("Highly speculative, in or very near default", NP, CreditRatingBand.C),
    C("Lowest rated, typically in default", NP, CreditRatingBand.D),
    NR("Not rated", MoodysGlobalShortTermRating.NR, CreditRatingBand.U),
    WR("Withdrawn", MoodysGlobalShortTermRating.NR, CreditRatingBand.U),
    NAV("Not available", MoodysGlobalShortTermRating.NR, CreditRatingBand.U);

    private final String description;
    private final CreditRatingBand band;
    private final MoodysGlobalShortTermRating globalShortTerm;

    private MoodysLongTermRating(
            final String description,
            final MoodysGlobalShortTermRating globalShortTerm,
            final CreditRatingBand band) {
        this.description = description;
        this.globalShortTerm = globalShortTerm;
        this.band = band;
    }

    @Override
    public String description() {
        return description;
    }

    public MoodysGlobalShortTermRating globalShortTermEquivalent() {
        return globalShortTerm;
    }

    @Override
    public boolean isInvestmentGrade() {
        return this.ordinal() <= Baa3.ordinal();
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
        return that instanceof MoodysLongTermRating
                ? this.compareTo((MoodysLongTermRating) that)
                : MoodysRating.super.compareTo(that);
    }

}
