package net.ollie.meerkat.temporal.business;

import java.time.LocalDate;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface BusinessDayConvention {

    @Nonnull
    LocalDate adjust(LocalDate date);

}
