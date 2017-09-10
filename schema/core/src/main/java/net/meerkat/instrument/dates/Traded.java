package net.meerkat.instrument.dates;

import java.time.LocalDate;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Traded {

    @Nonnull
    LocalDate tradeDate();

}
