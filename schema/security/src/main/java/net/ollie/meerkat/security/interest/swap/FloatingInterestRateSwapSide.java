package net.ollie.meerkat.security.interest.swap;

import net.ollie.meerkat.utils.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.earning.FloatingInterestEarning;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.numeric.interest.InterestRateId;

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
