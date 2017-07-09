package net.meerkat.instrument.interest.swap;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.interest.fixed.FixedInterestRate;

/**
 *
 * @author ollie
 */
public class FixedForFixedInterestRateSwapLeg<P extends CurrencyId, R extends CurrencyId>
        implements InterestRateSwapLeg<P, R> {

    private final FixedInterestRate payRate;
    private final P payCurrency;
    private final FixedInterestRate receiveRate;
    private final R receiveCurrency;

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

    @Override
    public FixedInterestRate payRate() {
        return payRate;
    }

    @Override
    public FixedInterestRate receiveRate() {
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
