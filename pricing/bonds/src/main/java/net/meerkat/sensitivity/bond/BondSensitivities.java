package net.meerkat.sensitivity.bond;

import javax.annotation.Nonnull;

import net.meerkat.calculate.sensitivity.Sensitivities;
import net.meerkat.calculate.sensitivity.yield.DollarDuration;

/**
 *
 * @author Ollie
 */
public interface BondSensitivities extends Sensitivities {

    @Nonnull
    DollarDuration dollarDuration();

}
