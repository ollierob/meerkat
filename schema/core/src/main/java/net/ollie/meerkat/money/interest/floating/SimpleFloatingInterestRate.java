package net.ollie.meerkat.money.interest.floating;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.money.Money;
import net.ollie.meerkat.money.currency.Currency;
import net.ollie.meerkat.money.interest.accrual.InterestAccrual;
import net.ollie.meerkat.money.interest.curve.YieldCurve;
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

    public SimpleFloatingInterestRate(final LocalDate spot, final YieldCurve<LocalDate> curve, final DateArithmetic accrual) {
        super(spot, accrual);
        this.curve = curve;
    }

    @Override
    public SimpleFloatingInterestRate plus(final Percentage bump) {
        return new SimpleFloatingInterestRate(this.referenceDate(), curve.plus(bump), this.dateArithmetic());
    }

    @Override
    public Percentage spot(final LocalDate end) {
        return curve.get(end, interpolator);
    }

    @Override
    public Percentage forward(final LocalDate start, final LocalDate end) {
        final Percentage r1 = this.spot(start);
        final Percentage r2 = this.spot(end);
        final Years d1 = this.yearsUntil(start);
        final Years d2 = this.yearsUntil(end);
        //([(1+r2d2)/(1+r1d1)]-1)/(d2-d1)
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    protected <C extends Currency> Money<C> accrue(final Money<C> money, final Percentage forwardRate, final LocalDate start, final LocalDate end) {
        return InterestAccrual.simple().accrue(money, forwardRate, this.dateArithmetic().yearsBetween(start, end));
    }

}
