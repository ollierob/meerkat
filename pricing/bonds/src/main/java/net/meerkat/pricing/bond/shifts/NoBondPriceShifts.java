package net.meerkat.pricing.bond.shifts;

import net.meerkat.pricing.shifts.fx.NoExchangeRateShifts;
import net.meerkat.pricing.shifts.interest.NoInterestRateShifts;

/**
 *
 * @author Ollie
 */
public class NoBondPriceShifts implements BondPriceShifts, NoInterestRateShifts, NoExchangeRateShifts {

    static final NoBondPriceShifts INSTANCE = new NoBondPriceShifts();

    protected NoBondPriceShifts() {
    }

}
