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
public enum MoodysLongTermRating implements MoodysRating {

    Aaa(AAA),
    Aa1(AA),
    Aa2(AA),
    Aa3(AA),
    A1(A),
    A2(A),
    A3(A),
    Baa1(BBB),
    Baa2(BBB),
    Baa3(BBB),
    Ba1(BB),
    Ba2(BB),
    Ba3(BB),
    B1(B),
    B2(B),
    B3(B),
    Caa1(CCC),
    Caa2(CC),
    Caa3(CreditRatingBand.C),
    Ca(CreditRatingBand.C),
    C(D),
    NR(CreditRatingBand.U), //Not rated
    WR(CreditRatingBand.U); //Withdrawn//Withdrawn

    private final CreditRatingBand band;

    private MoodysLongTermRating(CreditRatingBand band) {
        this.band = band;
    }

    @Override
    public boolean isInvestmentGrade() {
        return this.ordinal() <= Baa3.ordinal();
    }

    public CreditRatingBand band() {
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
