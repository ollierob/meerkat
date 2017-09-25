package net.meerkat.calculate.sensitivity.yield;

import net.meerkat.calculate.sensitivity.DollarSensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;
import net.meerkat.identifier.currency.USD;
import net.meerkat.money.Money;

/**
 * Sensitivity to a 1bp change in interest rates.
 *
 * @author ollie
 */
public class DollarDuration extends DollarSensitivity {

    private final SensitivityId<DollarDuration> id;

    public DollarDuration(final SensitivityId<DollarDuration> id, final Money<USD> dollars) {
        super(dollars);
        this.id = id;
    }

    @Override
    public SensitivityId<DollarDuration> sensitivityId() {
        return id;
    }

}
