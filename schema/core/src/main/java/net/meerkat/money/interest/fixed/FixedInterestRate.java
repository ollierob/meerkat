package net.meerkat.money.interest.fixed;

import java.time.LocalDate;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.count.DateArithmetic;
import net.ollie.goat.temporal.date.years.Years;

/**
 *
 * @author ollie
 */
@XmlRootElement
public abstract class FixedInterestRate implements InterestRate, Comparable<FixedInterestRate> {

    @XmlAttribute(name = "annual_rate")
    private Percentage annualRate;

    @XmlElementRef(name = "year_count")
    private DateArithmetic dates;

    @Deprecated
    protected FixedInterestRate() {
    }

    protected FixedInterestRate(
            final Percentage annualRate,
            final DateArithmetic dates) {
        this.annualRate = annualRate;
        this.dates = dates;
    }

    @Nonnull
    public Percentage annualRate() {
        return annualRate;
    }

    @Override
    public DateArithmetic dateArithmetic() {
        return dates;
    }

    public boolean isNegative() {
        return this.annualRate().isNegative();
    }

    @Override
    public FixedInterestRate plus(final Percentage bump) {
        return this.with(this.annualRate().plus(bump));
    }

    @Override
    public int compareTo(final FixedInterestRate that) {
        return this.annualRate().compareTo(that.annualRate());
    }

    public abstract FixedInterestRate with(Percentage annualRate);

    public abstract <C extends CurrencyId> Money<C> accrue(Money<C> money, Years term);

    @Override
    @Deprecated
    public <C extends CurrencyId> Money<C> accrue(
            final Money<C> money,
            final LocalDate from,
            final LocalDate until,
            final InterestRateInterpolator interpolator) {
        return this.accrue(money, from, until);
    }

    public <C extends CurrencyId> Money<C> accrue(
            final Money<C> money,
            final LocalDate from,
            final LocalDate until) {
        return this.accrue(money, dates.yearsBetween(from, until));
    }

    @Override
    @Deprecated
    public <C extends CurrencyId> Money<C> discount(
            final Money<C> money,
            final LocalDate earlier,
            final LocalDate later,
            final InterestRateInterpolator interpolator) {
        return this.discount(money, earlier, later);
    }

    public <C extends CurrencyId> Money<C> discount(
            final Money<C> money,
            final LocalDate earlier,
            final LocalDate later) {
        return this.accrue(money, later, earlier);
    }

}
