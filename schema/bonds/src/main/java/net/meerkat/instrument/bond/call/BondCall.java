package net.meerkat.instrument.bond.call;

import net.meerkat.numeric.percentage.Percentage;

import javax.annotation.Nonnull;
import java.time.LocalDate;

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
