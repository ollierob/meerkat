package net.meerkat.instrument.equity.future;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.identifier.instrument.future.FutureTicker;
import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.derivative.forward.Future;
import net.meerkat.instrument.equity.EquityDerivative;

/**
 *
 * @author ollie
 */
public interface EquityFuture<I extends Instrument> extends Future<I>, EquityDerivative<I> {

    @Nonnull
    default Optional<FutureTicker> futureTicker() {
        return this.instrumentId(FutureTicker.class);
    }

}
