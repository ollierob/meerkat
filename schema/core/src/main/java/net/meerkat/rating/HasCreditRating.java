package net.meerkat.rating;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface HasCreditRating {

    @Nonnull
    CreditRating creditRating();

}
