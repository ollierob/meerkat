package net.ollie.meerkat.calculate.sensitivity.greek;

import net.ollie.goat.numeric.fraction.DecimalFraction;
import net.ollie.meerkat.utils.Accumulator;

/**
 * Sensitivity to interest rate.
 *
 * @author Ollie
 */
public class Rho implements Greek<DecimalFraction> {

    @Override
    public Accumulator.Homogeneous<DecimalFraction> accumulator() {
        return DecimalFraction::plus;
    }

}
