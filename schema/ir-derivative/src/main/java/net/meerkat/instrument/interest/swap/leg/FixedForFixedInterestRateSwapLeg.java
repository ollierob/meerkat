package net.meerkat.instrument.interest.swap.leg;

import java.time.LocalDate;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.interest.fixed.FixedInterestRate;

/**
 *
 * @author ollie
 */
public class FixedForFixedInterestRateSwapLeg<P extends CurrencyId, R extends CurrencyId>
        extends AbstractInterestRateSwapLeg<P, R, FixedInterestRate, FixedInterestRate> {

    public FixedForFixedInterestRateSwapLeg(
            final LocalDate date,
            final P payCurrency,
            final FixedInterestRate payRate,
            final R receiveCurrency,
            final FixedInterestRate receiveRate) {
        super(date, payRate, payCurrency, receiveRate, receiveCurrency);
    }

}
