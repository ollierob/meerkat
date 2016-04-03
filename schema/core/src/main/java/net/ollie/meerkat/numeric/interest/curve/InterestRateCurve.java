package net.ollie.meerkat.numeric.interest.curve;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlElementWrapper;

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

    @XmlElementWrapper
    private NavigableMap<LocalDate, Percentage> data;

    public InterestRateCurve(final Map<LocalDate, Percentage> data) {
        this.data = new TreeMap<>(data);
    }

    @Override
    public Percentage at(final LocalDate date) {
        return data.get(date);
    }

    @Override
    public Percentage get(final LocalDate date, final Interpolator<LocalDate, Percentage> interpolator) {
        return interpolator.interpolate(date, data);
    }

    public InterestRateCurve plus(final Percentage bump) {
        throw new UnsupportedOperationException();
    }

}
