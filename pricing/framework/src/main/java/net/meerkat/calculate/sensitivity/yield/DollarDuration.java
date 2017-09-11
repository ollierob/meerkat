package net.meerkat.calculate.sensitivity.yield;

import net.meerkat.calculate.sensitivity.DollarSensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;
import net.meerkat.identifier.currency.USD;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public class DollarDuration extends DollarSensitivity {

    public DollarDuration(final Money<USD> dollars) {
        super(dollars);
    }

    @Override
    public String name() {
        return "DV01";
    }

    @Override
    public SensitivityId<?> sensitivityId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
