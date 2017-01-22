package net.meerkat.security.interest.swap;

import net.ollie.goat.numeric.percentage.Percentage;
import net.meerkat.numeric.interest.earning.FloatingInterestEarning;
import net.meerkat.money.Money;
import net.meerkat.numeric.interest.InterestRateId;

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
