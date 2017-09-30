package net.meerkat.pricing.moneymarket.shifts;

import net.meerkat.money.interest.InterestRate;
import net.meerkat.pricing.shifts.WrappedInstrumentPriceShifts;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;

/**
 *
 * @author Ollie
 */
public class WrappedRepoShifts extends WrappedInstrumentPriceShifts implements RepoShifts {

    public WrappedRepoShifts(final InstrumentPriceShifts shifts) {
        super(shifts);
    }

    @Override
    public InterestRate shiftRepoRate(InterestRate rate) {
        return rate;
    }

}
