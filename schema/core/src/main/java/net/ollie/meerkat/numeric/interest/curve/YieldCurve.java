package net.ollie.meerkat.numeric.interest.curve;

import java.time.LocalDate;
import java.util.NavigableMap;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.identifier.currency.HasCurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.utils.HasName;
import net.ollie.meerkat.utils.numeric.interpolation.Interpolator;
import net.ollie.meerkat.utils.numeric.manifold.Curve;

/**
 *
 * @author Ollie
 */
public class YieldCurve implements Curve<Tenor, Percentage>, HasName, HasCurrencyId {

    @XmlAttribute(name = "name")
    private String name;

    @XmlElementWrapper
    private NavigableMap<Tenor, Percentage> data;

    @XmlAttribute(name = "currency")
    private CurrencyId currency;

    @Override
    public String name() {
        return name;
    }

    @Override
    public CurrencyId currency() {
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

}
