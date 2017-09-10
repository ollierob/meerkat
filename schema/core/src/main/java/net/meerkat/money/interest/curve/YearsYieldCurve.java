package net.meerkat.money.interest.curve;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import net.ollie.goat.collection.Sets;
import net.ollie.goat.numeric.interpolation.Interpolator;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.years.Years;

/**
 * A {@link YieldCurve yield curve} whose x-axis is formed of {@link Years amounts of years}.
 *
 * @author Ollie
 */
public class YearsYieldCurve extends MappedYieldCurve<Years, YearsYieldCurve> {

    public YearsYieldCurve(final Map<Years, Percentage> data) {
        super(data, Comparator.naturalOrder());
    }

    @Override
    protected YearsYieldCurve with(final Map<Years, Percentage> curve) {
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
    public Map.Entry<Years, Percentage> interpolate(final Tenor tenor, final Interpolator<Years, Percentage> interpolator) {
        final Years years = Years.of(tenor.period());
        return this.interpolate(years, interpolator);
    }

    @Override
    public DateYieldCurve resolve(final LocalDate referenceDate) {
        final Map<Years, Percentage> in = this.toMap();
        final Map<LocalDate, Percentage> out = new HashMap<>(in.size());
        in.forEach((years, rate) -> out.put(referenceDate.plus(years.period()), rate));
        return new DateYieldCurve(referenceDate, out);
    }

}
