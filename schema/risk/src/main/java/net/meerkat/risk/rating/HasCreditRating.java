package net.meerkat.risk.rating;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface HasCreditRating {

    @Nonnull
    CreditRating creditRating();

}
