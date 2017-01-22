package net.ollie.meerkat.money.interest.curve;

import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.collection.Maps;
import net.ollie.goat.collection.Sets;
import net.ollie.goat.numeric.interpolation.Interpolator;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.years.Years;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class YearsYieldCurve
        implements YieldCurve<Years> {

    @XmlElementWrapper
    private NavigableMap<Years, Percentage> data;

    @Deprecated
    YearsYieldCurve() {
    }

    public YearsYieldCurve(final Map<Years, Percentage> data) {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Cannot construct yield curve from no points!");
        }
        this.data = new TreeMap<>(data);
    }

    public Set<Years> yearFractions() {
        return Collections.unmodifiableSet(data.keySet());
    }

    @Override
    public NavigableMap<Years, Percentage> toMap() {
        return Collections.unmodifiableNavigableMap(data);
    }

    @Override
    public Percentage at(final Years tenor) {
        return data.get(tenor);
    }

    @Override
    public Percentage get(final Years tenor, final Interpolator<Years, Percentage> interpolator) {
        return interpolator.interpolate(tenor, data);
    }

    @Override
    public YearsYieldCurve plus(final Percentage bump) {
        return new YearsYieldCurve(Maps.eagerlyTransformValues(data, rate -> rate.plus(bump)));
    }

    public YearsYieldCurve plus(final YearsYieldCurve that, final Interpolator<Years, Percentage> interpolator) {
        final NavigableMap<Years, Percentage> data = new TreeMap<>();
        final Set<Years> tenors = Sets.eagerUnion(this.yearFractions(), that.yearFractions());
        tenors.forEach(tenor -> {
            final Percentage rate = this.get(tenor, interpolator).plus(that.get(tenor, interpolator));
            data.put(tenor, rate);
        });
        return new YearsYieldCurve(data);
    }

    public boolean isFlat() {
        return data.size() == 1; //TODO or all values equal
    }

}
