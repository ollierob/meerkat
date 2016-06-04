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
public class FloorFeature implements RateFeature {

    @XmlAttribute(name = "min")
    private Percentage minRate;

    @Deprecated
    FloorFeature() {
    }

    public FloorFeature(final Percentage minRate) {
        this.minRate = minRate;
    }

    @Override
    public Percentage apply(final Percentage rate) {
        return Comparators.max(minRate, rate);
    }

}
