package net.meerkat.money.price;

import java.util.List;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.ollie.goat.collection.Collections;

/**
 *
 * @author Ollie
 */
public interface TwoWayPriceStack<C extends CurrencyId> {

    @Nonnull
    List<Money<C>> bids();

    @Nonnull
    List<Money<C>> offers();

    @CheckForNull
    default Money<C> bestBid() {
        final List<Money<C>> bids = this.bids();
        return bids.isEmpty() ? null : bids.get(0);
    }

    @CheckForNull
    default Money<C> bestOffer() {
        final List<Money<C>> offers = this.offers();
        return offers.isEmpty() ? null : offers.get(0);
    }

    @SuppressWarnings("unchecked")
    default Money<C>[] bidArray() {
        return Collections.toArray(this.bids(), Money[]::new);
    }

    @SuppressWarnings("unchecked")
    default Money<C>[] offerArray() {
        return Collections.toArray(this.offers(), Money[]::new);
    }

}
