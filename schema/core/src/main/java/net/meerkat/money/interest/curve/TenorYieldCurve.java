package net.meerkat.money.interest.curve;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.numeric.interpolation.Interpolator;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 * A {@link YieldCurve yield curve} whose x-axis is formed of {@link Tenor tenors}.
 *
 * @author ollie
 */
@XmlRootElement
public class TenorYieldCurve extends MappedYieldCurve<Tenor, TenorYieldCurve> {

    public TenorYieldCurve(final Map<Tenor, Percentage> curve) {
        super(curve, Comparator.naturalOrder());
    }

    @Override
    protected TenorYieldCurve with(final Map<Tenor, Percentage> curve) {
        return new TenorYieldCurve(curve);
    }

    @Override
    public Map.Entry<Tenor, Percentage> interpolate(final Tenor key, final Interpolator<Tenor, Percentage> interpolator) {
        return super.interpolate(key, interpolator);
    }

    @Override
    public DateYieldCurve resolve(final LocalDate referenceDate) {
        final Map<Tenor, Percentage> in = this.toMap();
        final Map<LocalDate, Percentage> out = new HashMap<>(in.size());
        in.forEach((tenor, rate) -> out.put(referenceDate.plus(tenor.period()), rate));
        return new DateYieldCurve(referenceDate, out);
    }

}
