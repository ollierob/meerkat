package net.ollie.meerkat.numeric.interest.curve;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;

import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.utils.HasName;
import net.ollie.meerkat.utils.numeric.interpolation.Interpolator;
import net.ollie.meerkat.utils.numeric.manifold.Curve;

/**
 *
 * @author ollie
 */
public class InterestRateCurve implements Curve<LocalDate, Percentage>, HasName {

    private static final LocalDate SOME_TIME = LocalDate.now();

    public static InterestRateCurve flat(final String name, final Percentage percentage) {
        return new InterestRateCurve(name, Collections.singletonMap(SOME_TIME, percentage));
    }

    @XmlAttribute(name = "name")
    private String name;

    @XmlElementWrapper
    private NavigableMap<LocalDate, Percentage> data;

    public InterestRateCurve(final String name, final Map<LocalDate, Percentage> data) {
        this.name = name;
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

    @Override
    public String name() {
        return name;
    }

    public InterestRateCurve plus(final Percentage bump) {
        throw new UnsupportedOperationException();
    }

}
