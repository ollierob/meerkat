package net.ollie.meerkat.money.interest.curve;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlElementWrapper;

import net.ollie.goat.collection.Maps;
import net.ollie.goat.numeric.interpolation.Interpolator;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public class DateYieldCurve implements YieldCurve<LocalDate> {

    private static final LocalDate SOME_TIME = LocalDate.now();

    public static DateYieldCurve flat(final Percentage percentage) {
        return new DateYieldCurve(Collections.singletonMap(SOME_TIME, percentage));
    }

    @XmlElementWrapper
    private NavigableMap<LocalDate, Percentage> data;

    public DateYieldCurve(final Map<LocalDate, Percentage> data) {
        this(new TreeMap<>(data));
    }

    private DateYieldCurve(final NavigableMap<LocalDate, Percentage> data) {
        this.data = data;
    }

    @Override
    public NavigableMap<LocalDate, Percentage> toMap() {
        return Collections.unmodifiableNavigableMap(data);
    }

    @Override
    public Percentage at(final LocalDate date) {
        return data.get(date);
    }

    @Override
    public Percentage get(final LocalDate date, final Interpolator<LocalDate, Percentage> interpolator) {
        return interpolator.interpolate(date, data);
    }

    @Override
    public DateYieldCurve plus(final Percentage bump) {
        return new DateYieldCurve(Maps.eagerlyTransformValues(data, d -> d.plus(bump)));
    }

}
