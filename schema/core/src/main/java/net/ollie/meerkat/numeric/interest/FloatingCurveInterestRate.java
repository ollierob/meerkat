package net.ollie.meerkat.numeric.interest;

import java.time.LocalDate;

import javax.annotation.CheckReturnValue;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.curve.InterestRateCurve;
import net.ollie.meerkat.numeric.interest.daycount.DayCount;
import net.ollie.meerkat.numeric.interest.interpolation.InterestRateInterpolator;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class FloatingCurveInterestRate implements FloatingInterestRate {

    public static FloatingCurveInterestRate flat(final String name, final Percentage rate, final InterestRateInterpolator interpolator) {
        return new FloatingCurveInterestRate(InterestRateCurve.flat(name, rate), interpolator);
    }

    @XmlElement(name = "curve")
    private InterestRateCurve curve;

    @XmlElementRef(name = "interpolation")
    private InterestRateInterpolator interpolator;

    @Deprecated
    FloatingCurveInterestRate() {
    }

    private FloatingCurveInterestRate(
            final InterestRateCurve curve,
            final InterestRateInterpolator interpolator) {
        this.curve = curve;
        this.interpolator = interpolator;
    }

    @Override
    public Percentage fixing(final LocalDate date) {
        return curve.get(date, interpolator);
    }

    public DayCount dayCount() {
        return interpolator.dayCount();
    }

    @Override
    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final LocalDate start, final LocalDate accrualDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FloatingCurveInterestRate plus(final Percentage bump) {
        return new FloatingCurveInterestRate(curve.plus(bump), interpolator);
    }

    @CheckReturnValue
    public FloatingCurveInterestRate with(final InterestRateInterpolator interpolator) {
        return new FloatingCurveInterestRate(curve, interpolator);
    }

    @Override
    public String name() {
        return curve.name();
    }

}
