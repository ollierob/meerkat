package net.ollie.meerkat.time.interim;

import java.time.LocalDate;
import java.util.Optional;

import javax.annotation.Nonnull;

import net.ollie.meerkat.utils.collections.Sequence;

/**
 *
 * @author ollie
 */
public interface Interim extends Sequence<LocalDate> {

    boolean contains(LocalDate date);

    @Nonnull
    Optional<Interval> closed();

}
