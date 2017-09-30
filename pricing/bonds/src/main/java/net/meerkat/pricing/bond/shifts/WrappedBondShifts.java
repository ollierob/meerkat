package net.meerkat.pricing.bond.shifts;

import net.meerkat.pricing.shifts.InstrumentPriceShifts;
import net.meerkat.pricing.shifts.WrappedInstrumentPriceShifts;
import net.meerkat.pricing.shifts.fx.ExchangeRateShifts;
import net.meerkat.pricing.shifts.interest.InterestRateShifts;

/**
 *
 * @author Ollie
 */
public class WrappedBondShifts extends WrappedInstrumentPriceShifts implements BondShifts {

    public WrappedBondShifts(final InstrumentPriceShifts shifts) {
        super(shifts);
    }

    public WrappedBondShifts(
            final InterestRateShifts interestRateShifts,
            final ExchangeRateShifts exchangeRateShifts) {
        super(interestRateShifts, exchangeRateShifts);
    }

}
