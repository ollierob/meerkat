package net.meerkat.instrument.interest.swap.leg;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.interest.fixed.FixedInterestRate;

/**
 *
 * @author ollie
 */
public class FixedForFixedInterestRateSwapLeg<P extends CurrencyId, R extends CurrencyId>
        extends AbstractInterestRateSwapLeg<P, R, FixedInterestRate, FixedInterestRate> {

    public FixedForFixedInterestRateSwapLeg(
            final P payCurrency,
            final FixedInterestRate payRate,
            final R receiveCurrency,
            final FixedInterestRate receiveRate) {
        super(payRate, payCurrency, receiveRate, receiveCurrency);
    }

}
