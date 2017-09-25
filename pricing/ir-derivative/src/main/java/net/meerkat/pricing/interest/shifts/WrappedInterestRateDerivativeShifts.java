package net.meerkat.pricing.interest.shifts;

import net.meerkat.pricing.shifts.fx.ExchangeRateShifts;
import net.meerkat.pricing.shifts.interest.InterestRateShifts;
import net.meerkat.pricing.shifts.SecurityShifts;
import net.meerkat.pricing.shifts.WrappedSecurityShifts;

/**
 *
 * @author Ollie
 */
public class WrappedInterestRateDerivativeShifts extends WrappedSecurityShifts implements InterestRateDerivativeShifts {

    public WrappedInterestRateDerivativeShifts(final SecurityShifts shifts) {
        super(shifts);
    }

    public WrappedInterestRateDerivativeShifts(final InterestRateShifts interestRateShifts, final ExchangeRateShifts exchangeRateShifts) {
        super(null, interestRateShifts, exchangeRateShifts);
    }

}
