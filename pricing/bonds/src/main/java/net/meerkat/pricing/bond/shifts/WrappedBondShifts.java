package net.meerkat.pricing.bond.shifts;

import net.meerkat.pricing.shifts.fx.ExchangeRateShifts;
import net.meerkat.pricing.shifts.interest.InterestRateShifts;
import net.meerkat.pricing.shifts.PriceShifts;
import net.meerkat.pricing.shifts.SecurityShifts;
import net.meerkat.pricing.shifts.WrappedSecurityShifts;

/**
 *
 * @author Ollie
 */
public class WrappedBondShifts extends WrappedSecurityShifts implements BondShifts {

    public WrappedBondShifts(final SecurityShifts shifts) {
        super(shifts);
    }

    public WrappedBondShifts(
            final PriceShifts priceShifts,
            final InterestRateShifts interestRateShifts,
            final ExchangeRateShifts exchangeRateShifts) {
        super(priceShifts, interestRateShifts, exchangeRateShifts);
    }

}
