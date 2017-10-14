package net.meerkat.risk.rating;

import net.meerkat.risk.rating.exception.UnavailableCreditRatingException;
import net.ollie.goat.data.CompositeProvider;

/**
 *
 * @author ollie
 */
public interface CreditRatingProvider<T, K> extends CompositeProvider<T, K, CreditRating, CreditRatingSnapshot<K>> {

    @Override
    default CreditRatingSnapshot<K> require(final T temporal) throws UnavailableCreditRatingException {
        return this.require(temporal, UnavailableCreditRatingException::new);
    }

    @Override
    default CreditRating require(final T temporal, final K key) throws UnavailableCreditRatingException {
        return CompositeProvider.super.require(temporal, key);
    }

}
