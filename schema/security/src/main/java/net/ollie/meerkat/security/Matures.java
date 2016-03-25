package net.ollie.meerkat.security;

import java.time.LocalDate;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface Matures {

    @Nonnull
    LocalDate matures();

}
