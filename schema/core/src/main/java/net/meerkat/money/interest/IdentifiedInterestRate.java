package net.meerkat.money.interest;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.count.DateArithmetic;

/**
 *
 * @author ollie
 */
public class IdentifiedInterestRate implements InterestRate, HasInterestRateId {

    @XmlElementRef(name = "id")
    private InterestRateId id;

    @XmlElementRef(name = "rate")
    private InterestRate interestRate;

    @Deprecated
    IdentifiedInterestRate() {
    }

    public IdentifiedInterestRate(InterestRateId id, InterestRate interestRate) {
        this.id = id;
        this.interestRate = interestRate;
    }

    @Override
    public DateArithmetic dateArithmetic() {
        return interestRate.dateArithmetic();
    }

    @Override
    public <C extends CurrencyId> Money<C> accrue(Money<C> money, LocalDate from, LocalDate until, InterestRateInterpolator interpolator) {
        return interestRate.accrue(money, from, until, interpolator);
    }

    @Override
    public InterestRate plus(final Percentage bump) {
        return interestRate.plus(bump);
    }

    @Override
    public InterestRateId interestRateId() {
        return id;
    }

}
