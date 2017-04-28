package net.meerkat.instrument.interest.future;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.derivative.forward.Future;

/**
 *
 * @author ollie
 */
public interface InterestRateFuture<C extends CurrencyId, U extends Instrument>
        extends Future<U>, HasCurrencyId {

    @Override
    C currencyId();

}
