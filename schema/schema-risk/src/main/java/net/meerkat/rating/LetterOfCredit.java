package net.meerkat.rating;

import java.util.Set;

/**
 *
 * @author ollie
 */
public class LetterOfCredit implements CreditRating {

    private final CreditRating underlying;

    public LetterOfCredit(final CreditRating underlying) {
        this.underlying = underlying;
    }

    @Override
    public boolean isInvestmentGrade() {
        return underlying.isInvestmentGrade();
    }

    @Override
    public String description() {
        return underlying.description();
    }

    @Override
    public Set<CreditRatingBand> bands() {
        return underlying.bands();
    }

    @Override
    public String agency() {
        return underlying.agency();
    }

}
