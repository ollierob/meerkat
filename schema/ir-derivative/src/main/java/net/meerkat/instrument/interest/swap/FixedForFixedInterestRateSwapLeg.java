package net.meerkat.instrument.interest.swap;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.interest.InterestRateId;
import net.meerkat.money.interest.fixed.FixedInterestRate;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class FixedForFixedInterestRateSwapLeg<P extends CurrencyId, R extends CurrencyId>
        implements InterestRateSwapLeg<P, R> {

    @XmlElementRef(name = "payRate")
    private FixedInterestRate payRate;

    @XmlElementRef(name = "payCurrency")
    private P payCurrency;

    @XmlElementRef(name = "floatingRate")
    private FixedInterestRate receiveRate;

    @XmlElementRef(name = "receiveCurrency")
    private R receiveCurrency;

    @Deprecated
    FixedForFixedInterestRateSwapLeg() {
    }

    public FixedForFixedInterestRateSwapLeg(
            final P payCurrency,
            final FixedInterestRate payRate,
            final R receiveCurrency,
            final FixedInterestRate receiveRate) {
        this.payRate = payRate;
        this.payCurrency = payCurrency;
        this.receiveRate = receiveRate;
        this.receiveCurrency = receiveCurrency;
    }

    public FixedInterestRate pay() {
        return payRate;
    }

    public FixedInterestRate receive() {
        return receiveRate;
    }

    @Override
    public InterestRateId payRate() {
        return payRate.interestRateId();
    }

    @Override
    public InterestRateId receiveRate() {
        return receiveRate.interestRateId();
    }

    @Override
    public P payCurrency() {
        return payCurrency;
    }

    @Override
    public R receiveCurrency() {
        return receiveCurrency;
    }

}
