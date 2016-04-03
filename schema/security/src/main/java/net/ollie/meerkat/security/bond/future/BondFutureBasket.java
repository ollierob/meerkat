package net.ollie.meerkat.security.bond.future;

import java.time.Period;

import javax.xml.bind.annotation.XmlAttribute;

import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.security.Security;

/**
 *
 * @author Ollie
 */
public class BondFutureBasket implements Security {

    @XmlAttribute(name = "min_maturity")
    private Period minMaturity;

    @XmlAttribute(name = "max_maturity")
    private Period maxMaturity;

    @XmlAttribute(name = "reference_yield")
    private Percentage referenceYield;

    @Deprecated
    BondFutureBasket() {
    }

    public BondFutureBasket(Period minMaturity, Period maxMaturity, Percentage referenceYield) {
        this.minMaturity = minMaturity;
        this.maxMaturity = maxMaturity;
        this.referenceYield = referenceYield;
    }

    public Percentage referenceYield() {
        return referenceYield;
    }

}
