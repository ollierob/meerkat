package net.meerkat.instrument.bond.dates;

import java.time.LocalDate;
import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.instrument.dates.Issued;
import net.meerkat.instrument.dates.Matures;

/**
 *
 * @author ollie
 */
public interface BondDates extends Issued {

    @Nonnull
    default Optional<LocalDate> possibleMaturityDate() {
        return this instanceof Matures
                ? Optional.of(((Matures) this).maturityDate())
                : Optional.empty();
    }

    default boolean isPerpetual() {
        return !this.possibleMaturityDate().isPresent();
    }

}
