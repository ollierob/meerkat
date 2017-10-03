package net.meerkat.calculate.interest;

import javax.annotation.Nonnull;

import net.meerkat.instrument.Instrument;
import net.meerkat.calculate.sensitivity.UnitPriceSensitivities;

/**
 *
 * @author ollie
 */
public interface InstrumentSensitivityCalculator<T, I extends Instrument> {

    @Nonnull
    UnitPriceSensitivities sensitivities(T temporal, I instrument);

}
