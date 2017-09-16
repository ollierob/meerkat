package net.meerkat.pricing.moneymarket.shifts;

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

}
