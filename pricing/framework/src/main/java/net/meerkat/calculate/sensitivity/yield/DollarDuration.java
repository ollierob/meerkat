package net.meerkat.calculate.sensitivity.yield;

import net.meerkat.calculate.sensitivity.DollarSensitivity;
import net.meerkat.calculate.sensitivity.Sensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;
import net.meerkat.identifier.currency.USD;
import net.meerkat.money.Money;

/**
 * Sensitivity to a 1bp change in interest rates.
 *
 * @author ollie
 */
public class DollarDuration extends DollarSensitivity implements Sensitivity.Summing<DollarDuration> {

    public static final SensitivityId<DollarDuration> ID = SensitivityId.summing("DV01", DollarDuration.class);

    public DollarDuration(final Money<USD> dollars) {
        super(dollars);
    }

    @Override
    public SensitivityId<?> sensitivityId() {
        return ID;
    }

    @Override
    public DollarDuration plus(final DollarDuration that) {
        return new DollarDuration(this.value().plus(that.value()));
    }

}
