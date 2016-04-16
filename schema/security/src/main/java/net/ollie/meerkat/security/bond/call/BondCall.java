package net.ollie.meerkat.security.bond.call;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.meerkat.utils.numeric.Percentage;

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
