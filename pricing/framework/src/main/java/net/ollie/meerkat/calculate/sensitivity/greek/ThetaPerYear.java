package net.ollie.meerkat.calculate.sensitivity.greek;

import net.ollie.meerkat.numeric.DecimalFraction;
import net.ollie.meerkat.utils.Accumulator;

/**
 *
 * @author Ollie
 */
public class ThetaPerYear implements Greek<DecimalFraction> {

    @Override
    public Accumulator.Homogeneous<DecimalFraction> accumulator() {
        return DecimalFraction.accumulator();
    }

}
