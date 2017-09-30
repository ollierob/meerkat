package net.meerkat.pricing.moneymarket.shifts;

import net.meerkat.money.interest.InterestRate;
import net.meerkat.pricing.shifts.fx.ExchangeRateShifts;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;

/**
 *
 * @author ollie
 */
public interface RepoShifts extends ExchangeRateShifts {

    InterestRate shiftRepoRate(InterestRate rate);
    
    static RepoShifts none() {
        return NoRepoShifts.INSTANCE;
    }

    static RepoShifts cast(final InstrumentPriceShifts shifts) {
        return shifts instanceof RepoShifts
                ? (RepoShifts) shifts
                : new WrappedRepoShifts(shifts);
    }

}
