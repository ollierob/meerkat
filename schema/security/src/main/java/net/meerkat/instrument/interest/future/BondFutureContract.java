package net.meerkat.instrument.interest.future;

import java.time.Period;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.money.Money;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.meerkat.numeric.interest.InterestRateSecurity;
import net.meerkat.security.Instrument;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class BondFutureContract
        implements Instrument, InterestRateSecurity {

    @XmlElementRef(name = "face_value")
    private Money<?> faceValue;

    @XmlElementRef(name = "reference_rate")
    private FixedInterestRate referenceRate;

    @XmlElementRef(name = "min_maturity")
    private Period minimumMaturity;

    @XmlElementRef(name = "max_maturity")
    private Period maximumMaturity;

    @Deprecated
    BondFutureContract() {
    }

    public BondFutureContract(
            final Money<?> faceValue,
            final FixedInterestRate referenceRate,
            final Period minimumMaturity,
            final Period maximumMaturity) {
        this.faceValue = faceValue;
        this.referenceRate = referenceRate;
        this.minimumMaturity = minimumMaturity;
        this.maximumMaturity = maximumMaturity;
    }

    public Money<?> faceValue() {
        return faceValue;
    }

    @Override
    public FixedInterestRate interestRate() {
        return referenceRate;
    }

    public Period minimumMaturity() {
        return minimumMaturity;
    }

    public Period maximumMaturity() {
        return maximumMaturity;
    }

}
