package net.ollie.meerkat.utils.time;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.meerkat.utils.numeric.Numeric;

/**
 *
 * @author ollie
 */
public interface Years extends Comparable<Years>, Numeric.Summable<Years> {

    @Nonnull
    LocalDate addTo(LocalDate date);

}
