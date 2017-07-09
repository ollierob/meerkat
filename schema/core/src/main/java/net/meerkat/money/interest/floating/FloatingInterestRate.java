package net.meerkat.money.interest.floating;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.count.DateArithmetic;
import net.ollie.goat.temporal.date.years.Years;

/**
 *
 * @author Ollie
 */
public abstract class FloatingInterestRate implements InterestRate {

    private final LocalDate referenceDate;
    private final DateArithmetic dates;

    protected FloatingInterestRate(
            final LocalDate referenceDate,
            final DateArithmetic dates) {
        this.referenceDate = referenceDate;
        this.dates = dates;
    }

    @Nonnull
    public LocalDate referenceDate() {
        return referenceDate;
    }

    @Override
    public DateArithmetic dateArithmetic() {
        return dates;
    }

    public Percentage spotRate(final LocalDate end, final InterestRateInterpolator interpolator) {
        return this.forwardRate(this.referenceDate(), end, interpolator);
    }

    @Override
    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final LocalDate start, final LocalDate end, final InterestRateInterpolator interpolator) {
        final Percentage impliedForwardRate = this.forwardRate(start, end, interpolator);
        return this.accrue(money, impliedForwardRate, start, end);
    }

    public abstract Percentage forwardRate(LocalDate start, LocalDate end, InterestRateInterpolator interpolator);

    protected abstract <C extends CurrencyId> Money<C> accrue(Money<C> money, Percentage forwardRate, LocalDate start, LocalDate end);

    protected Years yearsUntil(final LocalDate date) {
        return this.yearsBetween(referenceDate, date);
    }

    protected final Years yearsBetween(final LocalDate start, final LocalDate end) {
        return dates.yearsBetween(start, end);
    }

}
