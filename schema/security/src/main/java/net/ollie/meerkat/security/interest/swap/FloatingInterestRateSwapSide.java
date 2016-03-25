package net.ollie.meerkat.security.interest.swap;

import net.ollie.meerkat.numeric.interest.earning.FloatingInterestEarning;
import net.ollie.meerkat.numeric.interest.InterestRateKey;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.numeric.Percentage;

/**
 *
 * @author ollie
 */
public class FloatingInterestRateSwapSide extends FloatingInterestEarning implements InterestRateSwapSide {

    @Deprecated
    FloatingInterestRateSwapSide() {
    }

    public FloatingInterestRateSwapSide(final Money notional, final Percentage spread, final InterestRateKey basis) {
        super(notional, spread, basis);
    }

}
