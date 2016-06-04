package net.ollie.meerkat.calculate.sensitivity;

import net.ollie.goat.money.Money;
import net.ollie.meerkat.identifier.currency.USD;
import net.ollie.meerkat.utils.Accumulator;

/**
 * DV01
 *
 * @author ollie
 */
public class DollarDuration implements Sensitivity<Money<USD>> {

    @Override
    public Accumulator.Homogeneous<Money<USD>> accumulator() {
        return Money::plus;
    }

}
