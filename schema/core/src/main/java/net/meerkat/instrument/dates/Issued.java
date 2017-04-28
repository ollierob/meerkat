package net.meerkat.instrument.dates;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.meerkat.issuer.HasIssuerId;

/**
 *
 * @author ollie
 */
public interface Issued extends HasIssuerId {

    @Nonnull
    LocalDate issueDate();

}
