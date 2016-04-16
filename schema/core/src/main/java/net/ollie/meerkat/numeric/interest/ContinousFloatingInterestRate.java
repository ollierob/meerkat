package net.ollie.meerkat.numeric.interest;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.curve.YieldCurve;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.time.daycount.AccrualFactor;
import net.ollie.meerkat.utils.numeric.interpolation.Interpolator;
import net.ollie.meerkat.utils.time.Years;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class ContinousFloatingInterestRate implements FloatingInterestRate {

    @XmlAttribute(name = "spot")
    private LocalDate spot;

    @XmlElementRef(name = "curve")
    private YieldCurve curve;

    @XmlElementRef(name = "interpolator", required = true)
    private Interpolator<Years, Percentage> interpolator;

    @XmlElementRef(name = "accrual")
    private AccrualFactor accrual;

    @Deprecated
    ContinousFloatingInterestRate() {
    }

    public ContinousFloatingInterestRate(
            final LocalDate spot,
            final YieldCurve curve,
            final AccrualFactor accrual,
            final Interpolator<Years, Percentage> interpolator) {
        this.spot = spot;
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
        final Years years = accrual.yearsBetween(spot, date);
        return curve.get(years, interpolator);
    }

    @Override
    public LocalDate spot() {
        return spot;
    }

    @Override
    public Percentage rateOver(final Years term) {
        return curve.get(term, interpolator);
    }

    @Override
    public <C extends CurrencyId> Money<C> accrue(Money<C> money, Percentage forwardRate, LocalDate start, LocalDate end) {
        return new ContinuousFixedInterestRate(forwardRate, accrual).accrue(money, start, end);
    }

    @Override
    public ContinousFloatingInterestRate plus(final Percentage bump) {
        return bump.isZero()
                ? this
                : new ContinousFloatingInterestRate(spot, curve.plus(bump), accrual, interpolator);
    }

}
