package net.meerkat.instrument.interest.swap.leg;

import java.time.LocalDate;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRateId;

/**
 *
 * @author ollie
 */
public class GenericInterestRateSwapLeg<P extends CurrencyId, R extends CurrencyId>
        extends AbstractInterestRateSwapLeg<P, R, InterestRateId, InterestRateId> {

    public GenericInterestRateSwapLeg(final LocalDate payDate, InterestRateId payRate, Money<P> payNotional, InterestRateId receiveRate, Money<R> receiveNotional) {
        super(payDate, payRate, payNotional, receiveRate, receiveNotional);
    }

}
