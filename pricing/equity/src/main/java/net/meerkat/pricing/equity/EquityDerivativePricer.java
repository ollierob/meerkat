package net.meerkat.pricing.equity;

import net.meerkat.instrument.equity.EquityDerivative;
import net.meerkat.pricing.InstrumentPricer;

/**
 *
 * @author Ollie
 */
public interface EquityDerivativePricer<T, E extends EquityDerivative<?>> extends InstrumentPricer<T, E> {

}
