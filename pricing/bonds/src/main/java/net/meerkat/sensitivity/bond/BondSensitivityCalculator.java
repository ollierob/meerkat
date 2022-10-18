package net.meerkat.sensitivity.bond;

import net.meerkat.calculate.interest.InstrumentSensitivityCalculator;
import net.meerkat.instrument.bond.Bond;

/**
 * @author ollie
 * @see BondUnitPriceSensitivities
 */
public interface BondSensitivityCalculator<T, B extends Bond> extends InstrumentSensitivityCalculator<T, B> {

    @Override
    BondUnitPriceSensitivities sensitivities(T temporal, B bond);

}
