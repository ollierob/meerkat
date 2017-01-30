package net.meerkat.calculate.sensitivity;

import net.meerkat.money.Money;
import net.meerkat.identifier.currency.USD;
import net.meerkat.utils.Accumulator;

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
