package net.ollie.meerkat.security.bond.future;

import java.math.BigDecimal;
import java.time.Period;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.meerkat.security.Security;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class BondFutureContract implements Security {

    @XmlElementRef(name = "reference_yield")
    private Percentage referenceYield;

    @XmlElementRef(name = "min_maturity")
    private Period minimumMaturity;

    @XmlElementRef(name = "max_maturity")
    private Period maximumMaturity;

    @XmlAttribute(name = "nominal")
    private BigDecimal nominal;

    @Deprecated
    BondFutureContract() {
    }

    public BondFutureContract(
            final Percentage referenceYield,
            final Period minimumMaturity,
            final Period maximumMaturity,
            final BigDecimal nominal) {
        this.referenceYield = referenceYield;
        this.minimumMaturity = minimumMaturity;
        this.maximumMaturity = maximumMaturity;
        this.nominal = nominal;
    }

    public Percentage referenceYield() {
        return referenceYield;
    }

    public Period minimumMaturity() {
        return minimumMaturity;
    }

    public Period maximumMaturity() {
        return maximumMaturity;
    }

    public BigDecimal nominal() {
        return nominal;
    }

}
