package net.meerkat.pricing.moneymarket.shifts;

import net.meerkat.money.interest.InterestRate;
import net.meerkat.pricing.shifts.WrappedSecurityShifts;
import net.meerkat.pricing.shifts.InstrumentShifts;

/**
 *
 * @author Ollie
 */
public class WrappedRepoShifts extends WrappedSecurityShifts implements RepoShifts {

    public WrappedRepoShifts(final InstrumentShifts shifts) {
        super(shifts);
    }

    @Override
    public InterestRate shiftRepoRate(InterestRate rate) {
        return rate;
    }

}
