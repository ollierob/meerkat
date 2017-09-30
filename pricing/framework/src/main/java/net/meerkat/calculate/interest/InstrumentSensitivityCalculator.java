package net.meerkat.calculate.interest;

import javax.annotation.Nonnull;

import net.meerkat.calculate.sensitivity.InstrumentSensitivities;
import net.meerkat.instrument.Instrument;

/**
 *
 * @author ollie
 */
public interface InstrumentSensitivityCalculator<T, I extends Instrument> {

    @Nonnull
    InstrumentSensitivities sensitivities(T temporal, I instrument);

}
