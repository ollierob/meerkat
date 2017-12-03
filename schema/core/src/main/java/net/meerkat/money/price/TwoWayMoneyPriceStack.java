package net.meerkat.money.price;

import net.coljate.list.List;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * @author Ollie
 */
public interface TwoWayMoneyPriceStack<C extends CurrencyId> extends Price<C> {

    /**
     * @return bids, with the "best" first.
     */
    @Nonnull
    List<Money<C>> bids();

    /**
     * @return offers, with the "best" first.
     */
    @Nonnull
    List<Money<C>> offers();

    @CheckForNull
    default Money<C> bestBid() {
        return this.bids().firstOr(null);
    }

    @CheckForNull
    default Money<C> bestOffer() {
        return this.offers().firstOr(null);
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
