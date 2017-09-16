package net.meerkat.instrument.interest.swap.leg;

import java.time.LocalDate;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRateOrId;

/**
 *
 * @author ollie
 */
public abstract class AbstractInterestRateSwapLeg<P extends CurrencyId, R extends CurrencyId, IP extends InterestRateOrId, IR extends InterestRateOrId>
        implements InterestRateSwapLeg<P, R> {

    private final LocalDate date;
    private final IP payRate;
    private final Money<P> payNotional;
    private final IR receiveRate;
    private final Money<R> receiveNotional;

    protected AbstractInterestRateSwapLeg(final LocalDate date, IP payRate, Money<P> payNotional, IR receiveRate, Money<R> receiveNotional) {
        this.date = date;
        this.payRate = payRate;
        this.payNotional = payNotional;
        this.receiveRate = receiveRate;
        this.receiveNotional = receiveNotional;
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
    public Money<P> payNotional() {
        return payNotional;
    }

    @Override
    public Money<R> receiveNotional() {
        return receiveNotional;
    }

}
