package net.ollie.meerkat.calculate.sensitivity.greek;

import net.ollie.goat.numeric.fraction.DecimalFraction;
import net.ollie.meerkat.utils.Accumulator;

/**
 *
 * @author Ollie
 */
public class Delta implements Greek<DecimalFraction> {

    @Override
    public Accumulator.Homogeneous<DecimalFraction> accumulator() {
        return DecimalFraction::plus;
    }

}
