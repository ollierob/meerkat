package net.meerkat.instrument.dates;

import java.time.LocalDate;

import javax.annotation.Nonnull;

/**
 * @author ollie
 */
public interface Settles {

    /**
     * @return the date on which the transfer of cash or assets occurs.
     */
    @Nonnull
    LocalDate settlementDate();

}
