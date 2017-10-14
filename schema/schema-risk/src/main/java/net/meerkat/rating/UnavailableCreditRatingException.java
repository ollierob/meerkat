package net.meerkat.rating;

import net.ollie.goat.collection.Arrays;

/**
 *
 * @author ollie
 */
public class UnavailableCreditRatingException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnavailableCreditRatingException(final Object... ids) {
        super("Credit rating not available for " + Arrays.toString(ids));
    }

}
