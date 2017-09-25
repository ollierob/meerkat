package net.meerkat.pricing.interest.shifts;

import net.meerkat.pricing.shifts.fx.ExchangeRateShifts;
import net.meerkat.pricing.shifts.interest.InterestRateShifts;
import net.meerkat.pricing.shifts.SecurityShifts;

/**
 *
 * @author Ollie
 */
public interface InterestRateDerivativeShifts extends InterestRateShifts, ExchangeRateShifts {

    InterestRateDerivativeShifts NONE = null;

    static InterestRateDerivativeShifts cast(final SecurityShifts shifts) {
        return new WrappedInterestRateDerivativeShifts(shifts);
    }

}
