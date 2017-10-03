package net.meerkat.rating;

import java.time.LocalDate;

import net.ollie.goat.data.BiProvider;

/**
 *
 * @author ollie
 */
public interface CreditRatingProvider<K> extends BiProvider<LocalDate, K, CreditRating> {

    @Override
    default CreditRating require(final LocalDate date, final K key) throws UnavailableCreditRatingException {
        return this.require(date, key, UnavailableCreditRatingException::new);
    }

}
