package net.ollie.meerkat.identifier.rating.moodys;

import java.util.Collections;
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
public enum MoodysGlobalLongTermRating implements MoodysRating {

    Aaa("Highest quality, lowest level of credit risk", AAA),
    Aa1("High quality, very low credit risk", AA),
    Aa2("High quality, very low credit risk", AA),
    Aa3("High quality, very low credit risk", AA),
    A1("Upper-medium grade, low credit risk", A),
    A2("Upper-medium grade, low credit risk", A),
    A3("Upper-medium grade, low credit risk", A),
    Baa1("Medium grade, moderate credit risk", BBB),
    Baa2("Medium grade, moderate credit risk", BBB),
    Baa3("Medium grade, moderate credit risk", BBB),
    Ba1("Speculative, substantial credit risk", BB),
    Ba2("Speculative, substantial credit risk", BB),
    Ba3("Speculative, substantial credit risk", BB),
    B1("Speculative, high credit risk", B),
    B2("Speculative, high credit risk", B),
    B3("Speculative, high credit risk", B),
    Caa1("Speculative, very high credit risk", CreditRatingBand.CCC),
    Caa2("Speculative, very high credit risk", CreditRatingBand.CCC),
    Caa3("Speculative, very high credit risk ", CreditRatingBand.CC),
    Ca("Highly speculative, in or very near default", CreditRatingBand.C),
    C("Lowest rated, typically in default", CreditRatingBand.D),
    NR("Not rated", CreditRatingBand.U),
    WR("Withdrawn", CreditRatingBand.U);

    private final String description;
    private final CreditRatingBand band;

    private MoodysGlobalLongTermRating(final String description, final CreditRatingBand band) {
        this.description = description;
        this.band = band;
    }

    @Override
    public String description() {
        return description;
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
        return that instanceof MoodysGlobalLongTermRating
                ? this.compareTo((MoodysGlobalLongTermRating) that)
                : MoodysRating.super.compareTo(that);
    }

}
