package net.meerkat.instrument;

import java.time.LocalDate;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface Issued {

    @Nonnull
    LocalDate issueDate();

}
