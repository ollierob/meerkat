package net.meerkat.instrument.equity.future;

import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.derivative.forward.Future;
import net.meerkat.instrument.equity.EquityDerivative;

/**
 *
 * @author ollie
 */
public interface EquityFuture<I extends Instrument> extends Future<I>, EquityDerivative<I> {

}
