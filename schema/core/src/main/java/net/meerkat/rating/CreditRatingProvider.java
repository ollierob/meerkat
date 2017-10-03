package net.meerkat.rating;

import net.ollie.goat.data.Provider;

/**
 *
 * @author ollie
 */
public interface CreditRatingProvider<K> extends Provider<K, CreditRating> {

    @Override
    default CreditRating require(final K key) throws UnavailableCreditRatingException {
        return this.require(key, UnavailableCreditRatingException::new);
    }

}
