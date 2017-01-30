package net.meerkat.calculate.sensitivity.greek;

import net.ollie.goat.temporal.date.years.Years;
import net.meerkat.utils.Accumulator;

/**
 *
 * @author Ollie
 */
public class ThetaPerYear implements Greek<Years> {

    @Override
    public Accumulator.Homogeneous<Years> accumulator() {
        return Years::plus;
    }

}
