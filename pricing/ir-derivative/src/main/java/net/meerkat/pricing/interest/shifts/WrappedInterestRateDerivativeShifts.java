package net.meerkat.pricing.interest.shifts;

import net.meerkat.pricing.shifts.InstrumentPriceShifts;
import net.meerkat.pricing.shifts.WrappedInstrumentPriceShifts;
import net.meerkat.pricing.shifts.fx.ExchangeRateShifts;
import net.meerkat.pricing.shifts.interest.InterestRateShifts;

/**
 *
 * @author Ollie
 */
public class WrappedInterestRateDerivativeShifts extends WrappedInstrumentPriceShifts implements InterestRateDerivativeShifts {

    public WrappedInterestRateDerivativeShifts(final InstrumentPriceShifts shifts) {
        super(shifts);
    }

    public WrappedInterestRateDerivativeShifts(final InterestRateShifts interestRateShifts, final ExchangeRateShifts exchangeRateShifts) {
        super(interestRateShifts, exchangeRateShifts);
    }

}
