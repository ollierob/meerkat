package net.meerkat.calculate.sensitivity.yield;

import net.meerkat.calculate.sensitivity.DollarSensitivity;
import net.meerkat.calculate.sensitivity.Sensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;
import net.meerkat.identifier.currency.USD;
import net.meerkat.money.Money;

/**
 * Sensitivity to a one basis point change in interest rates.
 *
 * @author ollie
 */
public class DollarDuration
        extends DollarSensitivity<DollarDuration>
        implements Sensitivity.Summable<DollarDuration> {

    public static final SensitivityId<DollarDuration> ID = SensitivityId.summing("DV01", DollarDuration.class);

    public DollarDuration(final Money<USD> dollars) {
        super(dollars);
    }

    @Override
    public SensitivityId<?> sensitivityId() {
        return ID;
    }

    @Override
    protected DollarDuration with(final Money<USD> dollars) {
        return new DollarDuration(dollars);
    }

}
