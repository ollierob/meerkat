package net.meerkat.instrument.dates;

import net.meerkat.time.calendar.settlement.SettlementDate;

import javax.annotation.Nonnull;
import java.time.Period;

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
        return this.tradeDate().until(this.settlementDate().date());
    }

}
