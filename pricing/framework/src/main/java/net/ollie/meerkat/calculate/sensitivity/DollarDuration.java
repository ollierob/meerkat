package net.ollie.meerkat.calculate.sensitivity;

import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.utils.Accumulator;

/**
 * DV01
 *
 * @author ollie
 */
public class DollarDuration implements Sensitivity<Money> {

    @Override
    public Accumulator<Money, Money> accumulator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String name() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
