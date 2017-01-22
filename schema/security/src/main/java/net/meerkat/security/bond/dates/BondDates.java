package net.meerkat.security.bond.dates;

import java.time.LocalDate;
import java.util.Optional;

import net.meerkat.security.Issued;

/**
 *
 * @author ollie
 */
public interface BondDates extends Issued {

    Optional<LocalDate> maturity();

    default boolean isPerpetual() {
        return !this.maturity().isPresent();
    }

}
