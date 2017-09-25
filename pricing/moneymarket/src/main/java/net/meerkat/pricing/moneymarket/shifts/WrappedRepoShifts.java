package net.meerkat.pricing.moneymarket.shifts;

import net.meerkat.money.interest.InterestRate;
import net.meerkat.pricing.shifts.SecurityShifts;
import net.meerkat.pricing.shifts.WrappedSecurityShifts;

/**
 *
 * @author Ollie
 */
public class WrappedRepoShifts extends WrappedSecurityShifts implements RepoShifts {

    public WrappedRepoShifts(final SecurityShifts shifts) {
        super(shifts);
    }

    @Override
    public InterestRate shiftRepoRate(InterestRate rate) {
        return rate;
    }

}
