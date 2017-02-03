package net.meerkat.instrument.interest.repo.dates;

import java.time.LocalDate;
import java.util.Optional;

import javax.annotation.Nonnull;


/**
 *
 * @author ollie
 */
public interface RepoDates {

    @Nonnull
    LocalDate deal();

    @Nonnull
    LocalDate near();

    @Nonnull
    Optional<LocalDate> far();

    default boolean isOpen() {
        return !this.far().isPresent();
    }

    default boolean isOvernight() {
        return this.far().map(far -> far.equals(this.near().plusDays(1))).orElse(false);
    }

    default boolean isTerm() {
        return !this.isOpen() && !this.isOvernight();
    }

}
