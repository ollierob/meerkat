package net.meerkat.sensitivity.bond;

import net.meerkat.calculate.interest.InstrumentSensitivityCalculator;
import net.meerkat.instrument.bond.Bond;

/**
 *
 * @author ollie
 */
public interface BondSensitivityCalculator<T, B extends Bond> extends InstrumentSensitivityCalculator<T, B> {

    @Override
    BondInstrumentSensitivities sensitivities(T temporal, B bond);

}
