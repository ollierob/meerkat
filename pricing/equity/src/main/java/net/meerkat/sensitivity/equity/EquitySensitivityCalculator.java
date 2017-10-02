package net.meerkat.sensitivity.equity;

import net.meerkat.calculate.interest.InstrumentSensitivityCalculator;
import net.meerkat.instrument.equity.Equity;

/**
 *
 * @author ollie
 */
public interface EquitySensitivityCalculator<T, E extends Equity> extends InstrumentSensitivityCalculator<T, E> {

}
