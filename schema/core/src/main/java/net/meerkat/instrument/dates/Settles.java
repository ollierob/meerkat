package net.meerkat.instrument.dates;

import java.time.LocalDate;
import java.time.Period;

import javax.annotation.Nonnull;

/**
 * @author ollie
 */
public interface Settles extends Traded {

    /**
     * @return the date on which the transfer of cash or assets occurs.
     */
    @Nonnull
    LocalDate settlementDate();

    @Nonnull
    default Period tradeToSettlement() {
        return Period.between(this.tradeDate(), this.settlementDate());
    }

}
