package net.meerkat.instrument.interest.swap;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.interest.InterestRateId;
import net.meerkat.money.interest.fixed.FixedInterestRate;

/**
 *
 * @author ollie
 */
public class FixedForFixedRateSwapLeg<P extends CurrencyId, R extends CurrencyId>
        implements InterestRateSwapLeg<P, R> {

    private FixedInterestRate fixedRate;

    @XmlElementRef(name = "floatingRate")
    private InterestRateId floatingRate;

    @Override
    public InterestRateId payRate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public InterestRateId receiveRate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public P payCurrency() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public R receiveCurrency() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
