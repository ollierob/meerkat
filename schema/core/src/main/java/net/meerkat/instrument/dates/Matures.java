package net.meerkat.instrument.dates;

import java.time.LocalDate;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface Matures {

    /**
     * @return the date on which the principal amount of some security is (re)paid.
     */
    @Nonnull
    LocalDate maturityDate();

}
