package net.meerkat.identifier.rating.sandp;

import java.util.Collections;
import java.util.Set;

import net.meerkat.risk.rating.CreditRating;
import net.meerkat.risk.rating.CreditRatingBand;

/**
 *
 * @author ollie
 */
public enum SAndPLongTermRating implements SAndPRating {

    AAA("Extremely strong capacity to meet financial obligations", CreditRatingBand.AAA),
    AA_PLUS("Very strong capacity to meet financial obligations", CreditRatingBand.AA),
    AA("Very strong capacity to meet financial obligations", CreditRatingBand.AA),
    AA_MINUS("Very strong capacity to meet financial obligations", CreditRatingBand.AA),
    A_PLUS("Strong capacity to meet financial obligations", CreditRatingBand.A),
    A("Strong capacity to meet financial obligations", CreditRatingBand.A),
    A_MINUS("Strong capacity to meet financial obligations", CreditRatingBand.A),
    BBB_PLUS("Weakened capacity to meet financial obligations", CreditRatingBand.BBB),
    BBB("Weakened capacity to meet financial obligations", CreditRatingBand.BBB),
    BBB_MINUS("Weakened capacity to meet financial obligations", CreditRatingBand.BBB),
    BB_PLUS("Somewhat weak capacity to meet financial obligations", CreditRatingBand.BB),
    BB("Somewhat weak capacity to meet financial obligations", CreditRatingBand.BB),
    BB_MINUS("Somewhat weak capacity to meet financial obligations", CreditRatingBand.BB),
    B_PLUS("Weak capacity to meet financial obligations", CreditRatingBand.B),
    B("Weak capacity to meet financial obligations", CreditRatingBand.B),
    B_MINUS("Weak capacity to meet financial obligations", CreditRatingBand.B),
    CCC_PLUS("Unlikely to have capacity to meet financial obligations", CreditRatingBand.CCC),
    CCC("Unlikely to have capacity to meet financial obligations", CreditRatingBand.CCC),
    CCC_MINUS("Unlikely to have capacity to meet financial obligations", CreditRatingBand.CCC),
    CC("Default a virtual certainty", CreditRatingBand.CC),
    C_PLUS("Highly vulnerable to nonpayment", CreditRatingBand.C),
    C("Highly vulnerable to nonpayment", CreditRatingBand.C),
    C_MINUS("Highly vulnerable to nonpayment", CreditRatingBand.C),
    RD("Received default", CreditRatingBand.D),
    SD("Selective default", CreditRatingBand.D),
    D("Default", CreditRatingBand.D),
    NR("Not rated", CreditRatingBand.U);

    private final String description;
    private final CreditRatingBand band;

    private SAndPLongTermRating(final String description, final CreditRatingBand band) {
        this.description = description;
        this.band = band;
    }

    @Override
    public String description() {
        return description;
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
        return that instanceof SAndPLongTermRating
                ? this.compareTo((SAndPLongTermRating) that)
                : SAndPRating.super.compareTo(that);
    }

}
