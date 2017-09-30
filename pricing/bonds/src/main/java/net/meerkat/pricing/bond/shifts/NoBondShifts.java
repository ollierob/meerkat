package net.meerkat.pricing.bond.shifts;

import net.meerkat.pricing.shifts.fx.NoExchangeRateShifts;
import net.meerkat.pricing.shifts.interest.NoInterestRateShifts;

/**
 *
 * @author Ollie
 */
public class NoBondShifts implements BondShifts, NoInterestRateShifts, NoExchangeRateShifts {

    static final NoBondShifts INSTANCE = new NoBondShifts();

}
