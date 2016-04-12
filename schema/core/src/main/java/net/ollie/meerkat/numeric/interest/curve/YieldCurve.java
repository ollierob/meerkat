package net.ollie.meerkat.numeric.interest.curve;

import com.google.common.collect.Sets;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.identifier.currency.HasCurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.utils.numeric.interpolation.Interpolator;
import net.ollie.meerkat.utils.numeric.manifold.Curve;

/**
 *
 * @author Ollie
 */
public class YieldCurve<C extends CurrencyId>
        implements Curve<Tenor, Percentage>, HasCurrencyId {

    @XmlElementWrapper
    private NavigableMap<Tenor, Percentage> data;

    @XmlAttribute(name = "currency")
    private C currency;

    @Deprecated
    YieldCurve() {
    }

    public YieldCurve(final Map<Tenor, Percentage> data, final C currency) {
        this.data = new TreeMap<>(data);
        this.currency = currency;
    }

    public NavigableSet<Tenor> tenors() {
        return Collections.unmodifiableNavigableSet(data.navigableKeySet());
    }

    @Override
    public C currencyId() {
        return currency;
    }

    @Override
    public Percentage at(final Tenor tenor) {
        return data.get(tenor);
    }

    @Override
    public Percentage get(final Tenor tenor, final Interpolator<Tenor, Percentage> interpolator) {
        return interpolator.interpolate(tenor, data);
    }

    public InterestRateCurve relativeTo(final LocalDate date) {
        throw new UnsupportedOperationException();
    }

    public YieldCurve<C> plus(final YieldCurve<C> that, final Interpolator<Tenor, Percentage> interpolator) {
        final NavigableMap<Tenor, Percentage> data = new TreeMap<>();
        final Set<Tenor> tenors = Sets.union(this.tenors(), that.tenors());
        tenors.forEach(tenor -> {
            final Percentage rate = this.get(tenor, interpolator).plus(that.get(tenor, interpolator));
            data.put(tenor, rate);
        });
        return new YieldCurve<>(data, currency);
    }

}
