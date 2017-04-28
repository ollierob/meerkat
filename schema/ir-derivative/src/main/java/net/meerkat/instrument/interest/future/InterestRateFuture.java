package net.meerkat.instrument.interest.future;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.instrument.derivative.forward.Future;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public interface InterestRateFuture<C extends CurrencyId, U extends InterestRateFutureContract<C>>
        extends Future<U>, HasCurrencyId {

    @Override
    @Nonnull
    U underlying();

    @Override
    default C currencyId() {
        return this.notional().currencyId();
    }

    @Nonnull
    default Money<C> notional() {
        return this.underlying().notional();
    }

}
