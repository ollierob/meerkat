package net.meerkat.instrument.dates;

import java.time.LocalDate;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface Traded {

    @Nonnull
    LocalDate tradeDate();

}
