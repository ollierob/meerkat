package net.meerkat.money.interest.floating;

import java.math.MathContext;
import java.time.LocalDate;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.curve.DateYieldCurve;
import net.meerkat.money.interest.fixed.ContinuousFixedInterestRate;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.Dates;
import net.ollie.goat.temporal.date.count.DateArithmetic;
import net.ollie.goat.temporal.date.years.Years;

/**
 *
 * @author ollie
 */
public class ContinousFloatingInterestRate extends FloatingInterestRate {

    private final DateYieldCurve curve;

    public ContinousFloatingInterestRate(
            final LocalDate referenceDate,
            final DateYieldCurve curve,
            final DateArithmetic dates) {
        super(referenceDate, dates);
        this.curve = curve;
    }

    @Override
    public Percentage spotRate(final LocalDate date, final InterestRateInterpolator interpolator) {
        return this.spotRate(this.yearsUntil(date), interpolator);
    }

    public Percentage spotRate(final Years term, final InterestRateInterpolator interpolator) {
        return curve.interpolateRate(term, interpolator);
    }

    @Override
    public Percentage forwardRate(final LocalDate start, final LocalDate end, final InterestRateInterpolator interpolator) {
        final LocalDate spot = this.referenceDate();
        if (Dates.equals(spot, start)) {
            return this.spotRate(end, interpolator);
        }
        final Years d1 = this.yearsBetween(spot, start);
        final Percentage r1 = this.spotRate(d1, interpolator);
        final Years d2 = this.yearsBetween(spot, end);
        final Percentage r2 = this.spotRate(d2, interpolator);
        final Years term = this.yearsBetween(start, end);
        //(r2*d2 - r1*d1) / (t2-t1)
        return (r2.times(d2.decimalValue()).minus(r1.times(d1.decimalValue())))
                .over(term.decimalValue(), MathContext.DECIMAL128);
    }

    @Override
    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final Percentage forwardRate, final LocalDate start, final LocalDate end) {
        return ContinuousFixedInterestRate.accrue(money, forwardRate, this.yearsBetween(start, end));
    }

    @Override
    public ContinousFloatingInterestRate plus(final Percentage bump) {
        return bump.isZero()
                ? this
                : new ContinousFloatingInterestRate(this.referenceDate(), curve.plus(bump), this.dateArithmetic());
    }

}
