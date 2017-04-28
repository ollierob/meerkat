package net.meerkat.money.interest.floating;

import java.util.Objects;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.money.interest.InterestRateId;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public class FloatingPlusSpreadInterestRateId implements InterestRateId {

    @XmlElementRef(name = "base")
    private InterestRateId base;

    @XmlElementRef(name = "spread")
    private Percentage spread;

    public FloatingPlusSpreadInterestRateId(final InterestRateId base, final Percentage spread) {
        this.base = base;
        this.spread = spread;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.base);
        hash = 97 * hash + Objects.hashCode(this.spread);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FloatingPlusSpreadInterestRateId other = (FloatingPlusSpreadInterestRateId) obj;
        if (!Objects.equals(this.base, other.base)) {
            return false;
        }
        if (!Objects.equals(this.spread, other.spread)) {
            return false;
        }
        return true;
    }

}
