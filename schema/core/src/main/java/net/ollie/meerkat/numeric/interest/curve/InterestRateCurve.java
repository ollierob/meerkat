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
 * @author ollie
 */
public class InterestRateCurve implements Curve<LocalDate, Percentage>, HasName, HasCurrencyId {

    @XmlAttribute(name = "name")
    private String name;

    @XmlElementWrapper
    private NavigableMap<LocalDate, Percentage> data;

    @XmlAttribute(name = "currency")
    private CurrencyId currency;

    @Override
    public CurrencyId currencyId() {
        return currency;
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
