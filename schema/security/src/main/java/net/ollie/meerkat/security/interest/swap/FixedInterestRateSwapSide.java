package net.ollie.meerkat.security.interest.swap;

import net.ollie.goat.money.interest.fixed.FixedInterestRate;
import net.ollie.meerkat.numeric.interest.earning.FixedInterestEarning;
import net.ollie.goat.money.Money;

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
