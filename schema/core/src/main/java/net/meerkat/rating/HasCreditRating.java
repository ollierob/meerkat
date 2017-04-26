package net.meerkat.rating;

import javax.annotation.CheckForNull;

/**
 *
 * @author ollie
 */
public interface HasCreditRating {

    @CheckForNull
    CreditRating creditRating();

}