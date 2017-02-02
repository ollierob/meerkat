package net.meerkat.instrument.interest.swap;

import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.meerkat.money.interest.earning.DefaultFixedInterestEarning;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public class FixedInterestRateSwapSide extends DefaultFixedInterestEarning implements InterestRateSwapSide {

    @Deprecated
    FixedInterestRateSwapSide() {
    }

    public FixedInterestRateSwapSide(final Money<?> notional, final FixedInterestRate rate) {
        super(notional, rate);
    }

}
