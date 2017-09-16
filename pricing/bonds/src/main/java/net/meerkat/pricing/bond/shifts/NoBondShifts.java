package net.meerkat.pricing.bond.shifts;

import net.meerkat.pricing.shifts.NoExchangeRateShifts;
import net.meerkat.pricing.shifts.NoInterestRateShifts;
import net.meerkat.pricing.shifts.NoPriceShifts;

/**
 *
 * @author Ollie
 */
public class NoBondShifts implements BondShifts, NoPriceShifts, NoInterestRateShifts, NoExchangeRateShifts {

    static final NoBondShifts INSTANCE = new NoBondShifts();

}
