package net.meerkat.calculate.interest;

import net.meerkat.calculate.sensitivity.Sensitivities;
import net.meerkat.instrument.Instrument;

/**
 *
 * @author ollie
 */
public interface InstrumentSensitivityCalculator<T, I extends Instrument> {

    Sensitivities sensitivities(T temporal, I instrument);

}
