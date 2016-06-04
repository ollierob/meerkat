package net.ollie.meerkat.calculate.price.interest.future;

import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.ollie.goat.money.currency.Currency;
import net.ollie.meerkat.security.interest.future.InterestRateFuture;

/**
 *
 * @author ollie
 */
public interface InterestRateFuturePricer<T extends Temporal> {

    @Nonnull
    <C extends Currency> InterestRateFuturePrice<C> price(T temporal, InterestRateFuture future, C currency);

}
