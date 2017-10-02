package net.meerkat.sensitivity.equity;

import net.meerkat.calculate.interest.InstrumentSensitivityCalculator;
import net.meerkat.instrument.equity.EquityDerivative;

/**
 *
 * @author ollie
 */
public interface EquityDerivativeSensitivityCalculator<T, E extends EquityDerivative<?>>
        extends InstrumentSensitivityCalculator<T, E> {

}
