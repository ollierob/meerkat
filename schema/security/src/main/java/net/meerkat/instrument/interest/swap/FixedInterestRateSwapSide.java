package net.meerkat.instrument.interest.swap;

import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.meerkat.numeric.interest.earning.FixedInterestEarning;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public class FixedInterestRateSwapSide extends FixedInterestEarning implements InterestRateSwapSide {

    @Deprecated
    FixedInterestRateSwapSide() {
    }

    public FixedInterestRateSwapSide(final Money<?> notional, final FixedInterestRate rate) {
        super(notional, rate);
    }

}
