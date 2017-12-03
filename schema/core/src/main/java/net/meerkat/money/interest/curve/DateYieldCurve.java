package net.meerkat.money.interest.curve;

import net.meerkat.money.interest.floating.ContinousFloatingInterestRate;
import net.meerkat.money.interest.floating.FloatingInterestRate;
import net.meerkat.numeric.interpolation.Interpolator;
import net.meerkat.numeric.percentage.Percentage;
import net.meerkat.temporal.date.count.DateArithmetic;
import net.meerkat.temporal.date.years.Years;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
 * A {@link YieldCurve yield curve} whose x-axis is formed of {@link LocalDate dates}.
 *
 * @author ollie
 */
public class DateYieldCurve extends MappedYieldCurve<LocalDate, DateYieldCurve> {

    public static DateYieldCurve flat(final LocalDate spot, final Percentage percentage) {
        return new DateYieldCurve(spot, Collections.singletonMap(spot, percentage));
    }

    private final LocalDate spot;

    public DateYieldCurve(final LocalDate spot, final Map<LocalDate, Percentage> curve) {
        super(curve, Comparator.naturalOrder());
        this.spot = spot;
    }

    @Override
    public Map.Entry<LocalDate, Percentage> interpolate(final Tenor tenor, final Interpolator<LocalDate, Percentage> interpolator) {
        final LocalDate date = spot.plus(tenor.period());
        return this.interpolate(date, interpolator);
    }

    public Map.Entry<LocalDate, Percentage> interpolate(final Years years, final Interpolator<LocalDate, Percentage> interpolator) {
        return this.interpolate(years.period(), interpolator);
    }

    public Percentage interpolateRate(final Years years, final Interpolator<LocalDate, Percentage> interpolator) {
        return this.interpolate(years, interpolator).getValue();
    }

    public Map.Entry<LocalDate, Percentage> interpolate(final Period period, final Interpolator<LocalDate, Percentage> interpolator) {
        final LocalDate date = spot.plus(period);
        return this.interpolate(date, interpolator);
    }

    @Override
    protected DateYieldCurve with(final Map<LocalDate, Percentage> curve) {
        return new DateYieldCurve(spot, curve);
    }

    @Override
    public DateYieldCurve resolve(final LocalDate referenceDate) {
        return spot.compareTo(referenceDate) == 0
                ? this
                : new DateYieldCurve(referenceDate, this.toMap());
    }

    public FloatingInterestRate toInterestRate(final DateArithmetic dateArithmetic) {
        return new ContinousFloatingInterestRate(spot, this, dateArithmetic);
    }

}
