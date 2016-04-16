package net.ollie.meerkat.numeric.interest;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.curve.InterestRateCurve;
import net.ollie.meerkat.numeric.interest.interpolation.InterestRateInterpolator;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.time.daycount.AccrualFactor;
import net.ollie.meerkat.utils.time.Years;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class ContinousFloatingInterestRate implements InterestRate {

    @XmlElementRef(name = "interpolator", required = true)
    private InterestRateInterpolator interpolator;

    @XmlElementRef(name = "curve")
    private InterestRateCurve curve;

    @XmlElementRef(name = "accrual")
    private AccrualFactor accrual;

    @Deprecated
    ContinousFloatingInterestRate() {
    }

    public ContinousFloatingInterestRate(
            final InterestRateInterpolator interpolator,
            final InterestRateCurve curve,
            final AccrualFactor accrual) {
        this.interpolator = interpolator;
        this.curve = curve;
        this.accrual = accrual;
    }

    @Override
    public AccrualFactor accrual() {
        return accrual;
    }

    @Override
    public Percentage fixing(final LocalDate date) {
        return curve.get(date, interpolator);
    }

    /**
     * Calculate and apply the implied rate over the period {@code end - start}.
     */
    @Override
    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final LocalDate start, final LocalDate end) {
        final Years years = accrual.yearsBetween(start, end);
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ContinousFloatingInterestRate plus(final Percentage bump) {
        return bump.isZero()
                ? this
                : new ContinousFloatingInterestRate(interpolator, curve.plus(bump), accrual);
    }

}
