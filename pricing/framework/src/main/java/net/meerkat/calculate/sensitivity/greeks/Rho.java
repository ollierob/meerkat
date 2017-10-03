package net.meerkat.calculate.sensitivity.greeks;

import net.meerkat.calculate.sensitivity.DoubleSensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;

/**
 *
 * @author ollie
 */
public class Rho extends DoubleSensitivity implements Greek {

    public static final SensitivityId<Rho> ID = SensitivityId.nonSumming("Rho", Rho.class);

    public Rho(double value) {
        super(value);
    }

    @Override
    public SensitivityId<? extends Greek> sensitivityId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
