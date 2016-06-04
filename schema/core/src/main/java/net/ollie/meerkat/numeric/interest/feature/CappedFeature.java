package net.ollie.meerkat.numeric.interest.feature;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.meerkat.utils.comparators.Comparators;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class CappedFeature implements RateFeature {

    @XmlAttribute(name = "max")
    private Percentage maxRate;

    @Deprecated
    CappedFeature() {
    }

    public CappedFeature(final Percentage maxRate) {
        this.maxRate = maxRate;
    }

    @Override
    public Percentage apply(final Percentage rate) {
        return Comparators.min(maxRate, rate);
    }

}
