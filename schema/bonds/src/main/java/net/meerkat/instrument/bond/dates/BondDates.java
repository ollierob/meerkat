package net.meerkat.instrument.bond.dates;

import java.time.LocalDate;
import java.util.Optional;

import net.meerkat.instrument.Issued;

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
