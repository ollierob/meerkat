package net.ollie.meerkat.numeric.interest.curve;

import com.google.common.collect.Sets;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.identifier.currency.HasCurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.time.Years;
import net.ollie.meerkat.utils.numeric.interpolation.Interpolator;
import net.ollie.meerkat.utils.numeric.manifold.Curve;

/**
 *
 * @author Ollie
 */
public class YieldCurve<C extends CurrencyId>
        implements Curve<Years, Percentage>, HasCurrencyId {

    @XmlElementWrapper
    private NavigableMap<Years, Percentage> data;

    @XmlAttribute(name = "currency")
    private C currency;

    @Deprecated
    YieldCurve() {
    }

    public YieldCurve(final Map<Years, Percentage> data, final C currency) {
        this.data = new TreeMap<>(data);
        this.currency = currency;
    }

    public Set<Years> yearFractions() {
        return Collections.unmodifiableSet(data.keySet());
    }

    @Override
    public NavigableMap<Years, Percentage> toMap() {
        return Collections.unmodifiableNavigableMap(data);
    }

    @Override
    public C currencyId() {
        return currency;
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

    public YieldCurve<C> plus(final YieldCurve<C> that, final Interpolator<Years, Percentage> interpolator) {
        final NavigableMap<Years, Percentage> data = new TreeMap<>();
        final Set<Years> tenors = Sets.union(this.yearFractions(), that.yearFractions());
        tenors.forEach(tenor -> {
            final Percentage rate = this.get(tenor, interpolator).plus(that.get(tenor, interpolator));
            data.put(tenor, rate);
        });
        return new YieldCurve<>(data, currency);
    }

    public boolean isFlat() {
        return data.size() == 1; //TODO or all values equal
    }

}
