package net.meerkat.money.interest.curve;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.numeric.interpolation.Interpolator;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 * Yield curve whose x-axis is dates.
 *
 * @author ollie
 */
@XmlRootElement
public class DateYieldCurve extends AbstractYieldCurve<LocalDate, DateYieldCurve> {

    public static DateYieldCurve flat(final LocalDate spot, final Percentage percentage) {
        return new DateYieldCurve(spot, Collections.singletonMap(spot, percentage));
    }

    @XmlAttribute(name = "spot")
    private LocalDate spot;

    @Deprecated
    DateYieldCurve() {
    }

    public DateYieldCurve(final LocalDate spot, final Map<LocalDate, Percentage> curve) {
        super(curve, Comparator.naturalOrder());
        this.spot = spot;
    }

    @Override
    public Map.Entry<LocalDate, Percentage> interpolate(final Period tenor, final Interpolator<LocalDate, Percentage> interpolator) {
        final LocalDate date = spot.plus(tenor);
        return this.interpolate(date, interpolator);
    }

    @Override
    protected DateYieldCurve toCurve(final Map<LocalDate, Percentage> curve) {
        return new DateYieldCurve(spot, curve);
    }

}
