package net.ollie.meerkat.numeric.interest.interpolation;

import java.time.LocalDate;
import java.util.Map;
import java.util.NavigableMap;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.daycount.DayCount;

import org.apache.commons.math3.fraction.Fraction;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class LinearInterestRateInterpolator implements InterestRateInterpolator {

    @XmlElementRef(name = "day_count")
    private DayCount dayCount;

    @Deprecated
    LinearInterestRateInterpolator() {
    }

    public LinearInterestRateInterpolator(final DayCount dayCount) {
        this.dayCount = dayCount;
    }

    @Override
    public DayCount dayCount() {
        return dayCount;
    }

    @Override
    public Percentage interpolate(final LocalDate date, final NavigableMap<LocalDate, Percentage> map) {
        final Map.Entry<LocalDate, Percentage> floor = map.floorEntry(date);
        final Map.Entry<LocalDate, Percentage> ceiling = map.ceilingEntry(date);
        final Fraction dayFraction = new Fraction(
                dayCount.daysBetween(floor.getKey(), date),
                dayCount.daysBetween(floor.getKey(), ceiling.getKey()));
        final Percentage increment = (ceiling.getValue().minus(floor.getValue())).times(dayFraction);
        return floor.getValue().plus(increment);
    }

}
