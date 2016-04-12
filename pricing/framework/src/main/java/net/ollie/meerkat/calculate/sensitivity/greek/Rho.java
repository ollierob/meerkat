package net.ollie.meerkat.calculate.sensitivity.greek;

import net.ollie.meerkat.numeric.DecimalFraction;
import net.ollie.meerkat.utils.Accumulator;

/**
 * Sensitivity to interest rate.
 *
 * @author Ollie
 */
public class Rho implements Greek<DecimalFraction> {

    @Override
    public Accumulator.Homogeneous<DecimalFraction> accumulator() {
        return DecimalFraction.accumulator();
    }

}
