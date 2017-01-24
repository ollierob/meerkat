package net.meerkat.instrument.interest.swap;

import net.ollie.goat.numeric.percentage.Percentage;
import net.meerkat.money.interest.earning.FloatingInterestEarning;
import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRateId;

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
