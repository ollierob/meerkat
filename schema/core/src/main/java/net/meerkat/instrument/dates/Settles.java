package net.meerkat.instrument.dates;

import java.time.Period;

import javax.annotation.Nonnull;

import net.meerkat.time.calendar.settlement.SettlementDate;

/**
 * @author ollie
 */
public interface Settles extends Traded {

    /**
     * @return the date on which the transfer of cash or assets occurs.
     */
    @Nonnull
    SettlementDate settlementDate();

    @Nonnull
    default Period tradeToSettlement() {
        return Period.between(this.tradeDate(), this.settlementDate().date());
    }

}
