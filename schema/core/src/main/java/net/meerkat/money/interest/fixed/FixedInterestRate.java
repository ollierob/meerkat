package net.meerkat.money.interest.fixed;

import net.meerkat.Explainable;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.meerkat.numeric.percentage.Percentage;
import net.meerkat.temporal.date.count.DateArithmetic;
import net.meerkat.temporal.date.years.Years;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author ollie
 */
public abstract class FixedInterestRate
        implements InterestRate, Explainable, Comparable<FixedInterestRate> {

    private final Percentage annualRate;
    private final DateArithmetic dates;

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
    public FixedInterestRate times(final Percentage bump) {
        return this.with(this.annualRate().times(bump));
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

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("annual rate", annualRate)
                .put("dates", dates)
                .put("type", this.type());
    }

    protected abstract String type();

    @Override
    public String toString() {
        return this.type() + '@' + this.annualRate();
    }

}
