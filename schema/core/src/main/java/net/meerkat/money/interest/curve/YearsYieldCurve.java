package net.meerkat.money.interest.curve;

import net.meerkat.collections.Sets;
import net.meerkat.numeric.interpolation.Interpolator;
import net.meerkat.numeric.percentage.Percentage;
import net.meerkat.temporal.date.years.Years;

import java.time.LocalDate;
import java.util.*;

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
