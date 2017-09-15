package net.meerkat.instrument.interest.swap.leg;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.interest.InterestRateId;

/**
 *
 * @author ollie
 */
public class GenericInterestRateSwapLeg<P extends CurrencyId, R extends CurrencyId>
        extends AbstractInterestRateSwapLeg<P, R, InterestRateId, InterestRateId> {

    public GenericInterestRateSwapLeg(InterestRateId payRate, P payCurrency, InterestRateId receiveRate, R receiveCurrency) {
        super(payRate, payCurrency, receiveRate, receiveCurrency);
    }

}
