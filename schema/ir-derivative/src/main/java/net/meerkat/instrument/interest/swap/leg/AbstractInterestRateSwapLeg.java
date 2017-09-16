package net.meerkat.instrument.interest.swap.leg;

import java.time.LocalDate;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.interest.InterestRateOrId;

/**
 *
 * @author ollie
 */
public abstract class AbstractInterestRateSwapLeg<P extends CurrencyId, R extends CurrencyId, IP extends InterestRateOrId, IR extends InterestRateOrId>
        implements InterestRateSwapLeg<P, R> {

    private final LocalDate date;
    private final IP payRate;
    private final P payCurrency;
    private final IR receiveRate;
    private final R receiveCurrency;

    protected AbstractInterestRateSwapLeg(final LocalDate date, IP payRate, P payCurrency, IR receiveRate, R receiveCurrency) {
        this.date = date;
        this.payRate = payRate;
        this.payCurrency = payCurrency;
        this.receiveRate = receiveRate;
        this.receiveCurrency = receiveCurrency;
    }

    @Override
    public LocalDate payDate() {
        return date;
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
        return payCurrency;
    }

    @Override
    public R receiveCurrency() {
        return receiveCurrency;
    }

}
