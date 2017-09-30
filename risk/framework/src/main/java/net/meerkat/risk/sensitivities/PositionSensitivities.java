package net.meerkat.risk.sensitivities;

import javax.annotation.Nonnull;

import net.meerkat.calculate.sensitivity.InstrumentSensitivities;
import net.meerkat.calculate.sensitivity.Sensitivities;
import net.meerkat.risk.position.Position;

/**
 *
 * @author Ollie
 */
public interface PositionSensitivities extends Sensitivities {

    @Nonnull
    Position position();

    @Nonnull
    InstrumentSensitivities instrumentSensitivities();

}
