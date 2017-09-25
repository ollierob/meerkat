package net.meerkat.pricing.moneymarket.shifts;

import net.meerkat.pricing.shifts.fx.ExchangeRateShifts;
import net.meerkat.pricing.shifts.SecurityShifts;

/**
 *
 * @author ollie
 */
public interface RepoShifts extends ExchangeRateShifts {

    static RepoShifts none() {
        return NoRepoShifts.INSTANCE;
    }

    static RepoShifts cast(final SecurityShifts shifts) {
        return shifts instanceof RepoShifts
                ? (RepoShifts) shifts
                : new WrappedRepoShifts(shifts);
    }

}
