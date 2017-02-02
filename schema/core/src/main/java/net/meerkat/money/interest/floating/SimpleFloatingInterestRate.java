package net.meerkat.money.interest.floating;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.curve.YieldCurve;
import net.meerkat.money.interest.fixed.SimpleFixedInterestRate;
import net.ollie.goat.numeric.interpolation.Interpolator;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.count.DateArithmetic;
import net.ollie.goat.temporal.date.years.Years;

/**
 *
 * @author Ollie
 */
public class SimpleFloatingInterestRate extends FloatingInterestRate {

    @XmlElementRef(name = "curve")
    private YieldCurve<LocalDate> curve;

    @XmlElementRef(name = "interpolator", required = true)
    private Interpolator<LocalDate, Percentage> interpolator;

    @Deprecated
    SimpleFloatingInterestRate() {
    }

    public SimpleFloatingInterestRate(
            final LocalDate spot,
            final YieldCurve<LocalDate> curve,
            final DateArithmetic accrual) {
        super(spot, accrual);
        this.curve = curve;
    }

    @Override
    public SimpleFloatingInterestRate plus(final Percentage bump) {
        return new SimpleFloatingInterestRate(this.referenceDate(), curve.plus(bump), this.dateArithmetic());
    }

    @Override
    public Percentage spotRate(final LocalDate end) {
        return curve.get(end, interpolator);
    }

    @Override
    public Percentage forwardRate(final LocalDate start, final LocalDate end) {
        final Percentage r1 = this.spotRate(start);
        final Percentage r2 = this.spotRate(end);
        final Years d1 = this.yearsUntil(start);
        final Years d2 = this.yearsUntil(end);
        //([(1+r2d2)/(1+r1d1)]-1)/(d2-d1)
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    protected <C extends CurrencyId> Money<C> accrue(final Money<C> money, final Percentage forwardRate, final LocalDate start, final LocalDate end) {
        return SimpleFixedInterestRate.accrue(money, forwardRate, this.dateArithmetic().yearsBetween(start, end));
    }

}
