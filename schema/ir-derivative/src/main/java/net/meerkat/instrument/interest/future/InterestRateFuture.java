package net.meerkat.instrument.interest.future;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.instrument.InstrumentSnapshot;
import net.meerkat.instrument.derivative.forward.Future;
import net.meerkat.instrument.interest.InterestRateDerivative;
import net.meerkat.money.Money;

import javax.annotation.Nonnull;

/**
 * @author ollie
 */
public interface InterestRateFuture<C extends CurrencyId, U extends InterestRateFutureContract<C>>
        extends InterestRateDerivative, Future<U>, HasCurrencyId {

    @Nonnull
    default Money<C> notional(final InstrumentSnapshot<? extends U> snapshot) {
        return this.underlying(snapshot).notional();
    }

    @Override
    default <R> R handleWith(final Handler<R> handler) {
        return handler.handle(this);
    }

}
