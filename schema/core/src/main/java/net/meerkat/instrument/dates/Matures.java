package net.meerkat.instrument.dates;

import javax.annotation.Nonnull;
import java.time.LocalDate;

/**
 *
 * @author ollie
 */
public interface Matures {

    /**
     * @return the date on which the principal value of some security is (re)paid.
     */
    @Nonnull
    LocalDate maturityDate();

}
