package net.ollie.meerkat.security.interest.swap;

import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.meerkat.numeric.interest.earning.FloatingInterestEarning;
import net.ollie.goat.money.Money;
import net.ollie.goat.money.interest.InterestRateId;

/**
 *
 * @author ollie
 */
public class FloatingInterestRateSwapSide extends FloatingInterestEarning implements InterestRateSwapSide {

    @Deprecated
    FloatingInterestRateSwapSide() {
    }

    public FloatingInterestRateSwapSide(final Money<?> notional, final Percentage spread, final InterestRateId basis) {
        super(notional, spread, basis);
    }

}
