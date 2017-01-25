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
public class ContinousFloatingInterestRate extends FloatingInterestRate {

    @XmlElementRef(name = "curve")
    private YieldCurve<Years> curve;

    @XmlElementRef(name = "interpolator", required = true)
    private Interpolator<Years, Percentage> interpolator;

    @Deprecated
    ContinousFloatingInterestRate() {
    }

    public ContinousFloatingInterestRate(
            final LocalDate referenceDate,
            final YieldCurve<Years> curve,
            final DateArithmetic dates,
            final Interpolator<Years, Percentage> interpolator) {
        super(referenceDate, dates);
        this.interpolator = interpolator;
        this.curve = curve;
    }

    @Override
    public Percentage spot(final LocalDate date) {
        final Years years = this.yearsUntil(date);
        return curve.get(years, interpolator);
    }

    @Override
    public Percentage forward(final LocalDate start, final LocalDate end) {
        final LocalDate spot = this.referenceDate();
        if (Dates.equals(spot, start)) {
            return this.spot(end);
        }
        final Years d1 = this.term(spot, start);
        final Percentage r1 = this.spot(d1);
        final Years d2 = this.term(spot, end);
        final Percentage r2 = this.spot(d2);
        final Years term = this.term(start, end);
        return (r2.times(d2.decimalValue()).minus(r1.times(d2.decimalValue()))).over(term.decimalValue(), MathContext.DECIMAL128);
    }

    public Percentage spot(final Years term) {
        return curve.get(term, interpolator);
    }

    @Override
    public <C extends CurrencyId> Money<C> accrue(Money<C> money, Percentage forwardRate, LocalDate start, LocalDate end) {
        return new ContinuousFixedInterestRate(forwardRate, this.dateArithmetic()).accrue(money, start, end);
    }

    @Override
    public ContinousFloatingInterestRate plus(final Percentage bump) {
        return bump.isZero()
                ? this
                : new ContinousFloatingInterestRate(this.referenceDate(), curve.plus(bump), this.dateArithmetic(), interpolator);
    }

}
