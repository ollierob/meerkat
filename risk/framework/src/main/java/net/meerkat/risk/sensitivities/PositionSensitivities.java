package net.meerkat.risk.sensitivities;

import javax.annotation.Nonnull;

import net.meerkat.calculate.sensitivity.InstrumentSensitivities;
import net.meerkat.calculate.sensitivity.Sensitivities;

/**
 *
 * @author Ollie
 */
public interface PositionSensitivities extends Sensitivities {

    @Nonnull
    InstrumentSensitivities instrumentSensitivities();

}
