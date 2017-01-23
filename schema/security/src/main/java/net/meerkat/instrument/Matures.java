package net.meerkat.instrument;

import java.time.LocalDate;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface Matures {

    @Nonnull
    LocalDate maturityDate();

}
