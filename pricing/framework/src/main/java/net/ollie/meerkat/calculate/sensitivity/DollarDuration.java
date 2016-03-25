package net.ollie.meerkat.calculate.sensitivity;

import net.ollie.meerkat.identifier.currency.USD;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.utils.Accumulator;

/**
 * DV01
 *
 * @author ollie
 */
public class DollarDuration implements Sensitivity<Money<USD>> {

    @Override
    public Accumulator<Money<USD>, Money<USD>> accumulator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String name() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
