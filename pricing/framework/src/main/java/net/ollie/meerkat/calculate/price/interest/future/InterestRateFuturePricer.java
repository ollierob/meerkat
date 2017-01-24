package net.ollie.meerkat.calculate.price.interest.future;

import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.meerkat.instrument.interest.future.InterestRateFuture;
import net.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface InterestRateFuturePricer<T extends Temporal> {

    @Nonnull
    <C extends CurrencyId> InterestRateFuturePrice<C> price(T temporal, InterestRateFuture future, C currency);

}
