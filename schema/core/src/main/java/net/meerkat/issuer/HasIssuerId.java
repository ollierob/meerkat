package net.meerkat.issuer;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface HasIssuerId {

    @Nonnull
    IssuerId issuerId();

}
