package net.meerkat.rating;

import java.util.Collections;
import java.util.Set;

import javax.xml.bind.annotation.XmlEnum;

/**
 *
 * @author Ollie
 */
@XmlEnum
public enum CreditRatingBand implements CreditRating {

    AAA("Prime"),
    AA("High grade"),
    A("Upper medium grade"),
    BBB("Lower medium grade"),
    BB("Speculative"),
    B("Highly speculative"),
    CCC("Substantial risk"),
    CC("Extremely speculative"),
    C("Close to default"),
    D("Defaulted"),
    U("Unrated");

    private final String description;

    private CreditRatingBand(final String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }

    @Override
    public Set<CreditRatingBand> bands() {
        return Collections.singleton(this);
    }

    @Override
    public CreditRatingBand bestBand() {
        return this;
    }

    @Override
    public boolean isInvestmentGrade() {
        return this.ordinal() <= BBB.ordinal();
    }

    @Override
    public boolean isDefault() {
        return this == D;
    }

    @Override
    public String agency() {
        return "Generic";
    }

}
