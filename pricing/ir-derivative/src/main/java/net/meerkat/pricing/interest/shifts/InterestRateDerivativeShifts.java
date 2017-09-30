package net.meerkat.pricing.interest.shifts;

import net.meerkat.pricing.shifts.fx.ExchangeRateShifts;
import net.meerkat.pricing.shifts.interest.InterestRateShifts;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;

/**
 *
 * @author Ollie
 */
public interface InterestRateDerivativeShifts extends InterestRateShifts, ExchangeRateShifts {

    InterestRateDerivativeShifts NONE = null;

    static InterestRateDerivativeShifts cast(final InstrumentPriceShifts shifts) {
        return new WrappedInterestRateDerivativeShifts(shifts);
    }

}
