package net.meerkat.calculate.sensitivity.greeks;

import net.meerkat.calculate.sensitivity.DoubleSensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;

/**
 *
 * @author ollie
 */
public class Delta extends DoubleSensitivity implements Greek {

    public static final SensitivityId<Delta> ID = SensitivityId.nonSumming(Delta.class);

    public Delta(final double value) {
        super(value);
    }

    @Override
    public SensitivityId<Delta> sensitivityId() {
        return ID;
    }

}
