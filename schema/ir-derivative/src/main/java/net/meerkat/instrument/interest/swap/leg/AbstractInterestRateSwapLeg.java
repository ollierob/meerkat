package net.meerkat.instrument.interest.swap.leg;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.interest.InterestRateOrId;

/**
 *
 * @author ollie
 */
public abstract class AbstractInterestRateSwapLeg<P extends CurrencyId, R extends CurrencyId, IP extends InterestRateOrId, IR extends InterestRateOrId>
        implements InterestRateSwapLeg<P, R> {

    private final IP payRate;
    private final P payCurrency;
    private final IR receiveRate;
    private final R receiveCurrency;

    protected AbstractInterestRateSwapLeg(IP payRate, P payCurrency, IR receiveRate, R receiveCurrency) {
        this.payRate = payRate;
        this.payCurrency = payCurrency;
        this.receiveRate = receiveRate;
        this.receiveCurrency = receiveCurrency;
    }

    @Override
    public IP payRate() {
        return payRate;
    }

    @Override
    public IR receiveRate() {
        return receiveRate;
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
