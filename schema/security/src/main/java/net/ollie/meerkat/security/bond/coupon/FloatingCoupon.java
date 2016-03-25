package net.ollie.meerkat.security.bond.coupon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.numeric.interest.InterestRate;
import net.ollie.meerkat.numeric.interest.InterestRateKey;
import net.ollie.meerkat.numeric.interest.feature.RateFeature;
import net.ollie.meerkat.numeric.Percentage;

/**
 *
 * @author ollie
 */
@XmlRootElement(name = "floating")
public class FloatingCoupon extends AbstractBondCoupon {

    @XmlElementRef(name = "key")
    private InterestRateKey key;

    @XmlAttribute(name = "spread")
    private Percentage spread;

    @XmlElementRef(name = "feature")
    private List<? extends RateFeature> features;

    public FloatingCoupon(
            final LocalDate paymentDate,
            final Percentage spread,
            final InterestRateKey key,
            final Set<? extends RateFeature> features) {
        super(paymentDate);
        this.key = key;
        this.spread = spread;
        this.features = new ArrayList<>(features);
    }

    @Override
    public Percentage spread() {
        return spread;
    }

    public Collection<? extends RateFeature> features() {
        return Collections.unmodifiableCollection(features);
    }

    public InterestRateKey referenceRateKey() {
        return key;
    }

    @Override
    public boolean hasReferenceRate() {
        return true;
    }

    public InterestRate rate(final Function<? super InterestRateKey, ? extends InterestRate> getRate) {
        Percentage spread = this.spread();
        for (final RateFeature feature : this.features) {
            spread = feature.apply(spread);
        }
        return getRate.apply(key).plus(spread);
    }

}
