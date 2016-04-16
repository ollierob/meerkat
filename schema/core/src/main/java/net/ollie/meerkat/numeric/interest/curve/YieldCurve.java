package net.ollie.meerkat.numeric.interest.curve;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.ollie.meerkat.utils.Require;
import net.ollie.meerkat.utils.numeric.Percentage;
import net.ollie.meerkat.utils.numeric.interpolation.Interpolator;
import net.ollie.meerkat.utils.numeric.manifold.Curve;
import net.ollie.meerkat.utils.time.Years;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class YieldCurve
        implements Curve<Years, Percentage> {

    @XmlElementWrapper
    private NavigableMap<Years, Percentage> data;

    @Deprecated
    YieldCurve() {
    }

    public YieldCurve(final Map<Years, Percentage> data) {
        Require.not(data.isEmpty(), () -> "Cannot construct yield curve from no points!");
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

    public InterestRateCurve relativeTo(final LocalDate date) {
        return InterestRateCurve.of(date, this);
    }

    public YieldCurve plus(final Percentage bump) {
        return new YieldCurve(Maps.transformValues(data, rate -> rate.plus(bump)));
    }

    public YieldCurve plus(final YieldCurve that, final Interpolator<Years, Percentage> interpolator) {
        final NavigableMap<Years, Percentage> data = new TreeMap<>();
        final Set<Years> tenors = Sets.union(this.yearFractions(), that.yearFractions());
        tenors.forEach(tenor -> {
            final Percentage rate = this.get(tenor, interpolator).plus(that.get(tenor, interpolator));
            data.put(tenor, rate);
        });
        return new YieldCurve(data);
    }

    public boolean isFlat() {
        return data.size() == 1; //TODO or all values equal
    }

}
