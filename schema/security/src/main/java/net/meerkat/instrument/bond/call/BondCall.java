package net.meerkat.instrument.bond.call;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author Ollie
 */
public interface BondCall {

    @Nonnull
    LocalDate earliest();

    @Nonnull
    Percentage premium();

}
