package net.meerkat.pricing.interest;

import net.meerkat.pricing.shifts.ExchangeRateShifts;
import net.meerkat.pricing.shifts.InterestRateShifts;

/**
 *
 * @author Ollie
 */
public interface InterestRateDerivativeShifts extends InterestRateShifts, ExchangeRateShifts {

    InterestRateDerivativeShifts NONE = null;

}
