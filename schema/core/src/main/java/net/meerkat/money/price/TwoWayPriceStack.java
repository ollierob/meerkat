package net.meerkat.money.price;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.coljate.list.ImmutableList;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
public interface TwoWayPriceStack<C extends CurrencyId> extends Price<C> {

    @Nonnull
    ImmutableList<Money<C>> bids();

    @Nonnull
    ImmutableList<Money<C>> offers();

    @CheckForNull
    default Money<C> bestBid() {
        return this.bids().first();
    }

    @CheckForNull
    default Money<C> bestOffer() {
        return this.offers().first();
    }

    @SuppressWarnings("unchecked")
    default Money<C>[] bidArray() {
        return this.bids().arrayCopy(Money[]::new);
    }

    @SuppressWarnings("unchecked")
    default Money<C>[] offerArray() {
        return this.offers().arrayCopy(Money[]::new);
    }

}
