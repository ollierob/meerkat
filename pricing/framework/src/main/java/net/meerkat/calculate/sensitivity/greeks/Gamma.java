package net.meerkat.calculate.sensitivity.greeks;

import net.meerkat.calculate.sensitivity.DoubleSensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;

/**
 *
 * @author ollie
 */
public class Gamma extends DoubleSensitivity implements Greek {

    public static final SensitivityId<Gamma> ID = SensitivityId.nonSumming("Gamma", Gamma.class);

    public Gamma(final double value) {
        super(value);
    }

    @Override
    public SensitivityId<? extends Greek> sensitivityId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
