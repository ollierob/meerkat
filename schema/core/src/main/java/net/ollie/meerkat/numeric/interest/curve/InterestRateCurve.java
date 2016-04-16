package net.ollie.meerkat.numeric.interest.curve;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import javax.annotation.CheckReturnValue;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.google.common.collect.Maps;

import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.utils.numeric.interpolation.Interpolator;
import net.ollie.meerkat.utils.numeric.manifold.Curve;

/**
 *
 * @author ollie
 */
public class InterestRateCurve implements Curve<LocalDate, Percentage> {

    private static final LocalDate SOME_TIME = LocalDate.now();

    public static InterestRateCurve flat(final Percentage percentage) {
        return new InterestRateCurve(Collections.singletonMap(SOME_TIME, percentage));
    }

    public static InterestRateCurve of(final LocalDate fixing, final YieldCurve yieldCurve) {
        final NavigableMap<LocalDate, Percentage> map = new TreeMap<>();
        yieldCurve.toMap().forEach((tenor, rate) -> map.put(tenor.addTo(fixing), rate));
        return new InterestRateCurve(map);
    }

    @XmlElementWrapper
    private NavigableMap<LocalDate, Percentage> data;

    public InterestRateCurve(final Map<LocalDate, Percentage> data) {
        this(new TreeMap<>(data));
    }

    private InterestRateCurve(final NavigableMap<LocalDate, Percentage> data) {
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

    @CheckReturnValue
    public InterestRateCurve plus(final Percentage bump) {
        return new InterestRateCurve((Map<LocalDate, Percentage>) Maps.transformValues(data, d -> d.plus(bump)));
    }

}
