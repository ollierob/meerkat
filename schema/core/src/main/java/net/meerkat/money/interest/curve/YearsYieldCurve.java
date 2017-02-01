package net.meerkat.money.interest.curve;

import java.time.Period;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.collection.Sets;
import net.ollie.goat.numeric.interpolation.Interpolator;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.years.Years;

/**
 * Yield curve whose x-axis is amounts of years.
 *
 * @author Ollie
 */
@XmlRootElement
public class YearsYieldCurve extends AbstractYieldCurve<Years, YearsYieldCurve> {

    @Deprecated
    YearsYieldCurve() {
    }

    public YearsYieldCurve(final Map<Years, Percentage> data) {
        super(data, Comparator.naturalOrder());
    }

    @Override
    protected YearsYieldCurve toCurve(final Map<Years, Percentage> curve) {
        return new YearsYieldCurve(curve);
    }

    public YearsYieldCurve plus(final YearsYieldCurve that, final Interpolator<Years, Percentage> interpolator) {
        final NavigableMap<Years, Percentage> data = new TreeMap<>();
        final Set<Years> tenors = Sets.eagerUnion(this.xAxis(), that.xAxis());
        tenors.forEach(tenor -> {
            final Percentage rate = this.get(tenor, interpolator).plus(that.get(tenor, interpolator));
            data.put(tenor, rate);
        });
        return new YearsYieldCurve(data);
    }

    @Override
    public Map.Entry<Years, Percentage> interpolate(final Period tenor, final Interpolator<Years, Percentage> interpolator) {
        final Years years = Years.of(tenor);
        return this.interpolate(years, interpolator);
    }

}
