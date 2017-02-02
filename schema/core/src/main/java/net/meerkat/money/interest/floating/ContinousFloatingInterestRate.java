package net.meerkat.money.interest.floating;

import java.math.MathContext;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.curve.YieldCurve;
import net.meerkat.money.interest.fixed.ContinuousFixedInterestRate;
import net.ollie.goat.numeric.interpolation.Interpolator;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.Dates;
import net.ollie.goat.temporal.date.count.DateArithmetic;
import net.ollie.goat.temporal.date.years.Years;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class ContinousFloatingInterestRate<K> extends FloatingInterestRate {

    @XmlElementRef(name = "curve", required = true)
    private YieldCurve<K> curve;

    @XmlElementRef(name = "interpolator", required = true)
    private Interpolator<K, Percentage> interpolator;

    @Deprecated
    ContinousFloatingInterestRate() {
    }

    public ContinousFloatingInterestRate(
            final LocalDate referenceDate,
            final YieldCurve<K> curve,
            final DateArithmetic dates,
            final Interpolator<K, Percentage> interpolator) {
        super(referenceDate, dates);
        this.interpolator = interpolator;
        this.curve = curve;
    }

    @Override
    public Percentage spotRate(final LocalDate date) {
        return this.spotRate(this.yearsUntil(date));
    }

    public Percentage spotRate(final Years term) {
        return curve.interpolateRate(term.period(), interpolator);
    }

    @Override
    public Percentage forwardRate(final LocalDate start, final LocalDate end) {
        final LocalDate spot = this.referenceDate();
        if (Dates.equals(spot, start)) {
            return this.spotRate(end);
        }
        final Years d1 = this.yearsBetween(spot, start);
        final Percentage r1 = this.spotRate(d1);
        final Years d2 = this.yearsBetween(spot, end);
        final Percentage r2 = this.spotRate(d2);
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
    public ContinousFloatingInterestRate<K> plus(final Percentage bump) {
        return bump.isZero()
                ? this
                : new ContinousFloatingInterestRate<>(this.referenceDate(), curve.plus(bump), this.dateArithmetic(), interpolator);
    }

}
