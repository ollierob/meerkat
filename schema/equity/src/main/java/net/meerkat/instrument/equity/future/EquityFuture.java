package net.meerkat.instrument.equity.future;

import net.meerkat.identifier.instrument.future.FutureTicker;
import net.meerkat.instrument.derivative.forward.Future;
import net.meerkat.instrument.equity.Equity;
import net.meerkat.instrument.equity.EquityDerivative;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * @author ollie
 * @see IndexFuture
 * @see StockFuture
 */
public interface EquityFuture<E extends Equity> extends Future<E>, EquityDerivative<E> {

    @Nonnull
    default Optional<FutureTicker> futureTicker() {
        return this.instrumentId(FutureTicker.class);
    }

}
