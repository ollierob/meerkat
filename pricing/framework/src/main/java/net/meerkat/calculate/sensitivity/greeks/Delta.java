package net.meerkat.calculate.sensitivity.greeks;

import net.meerkat.calculate.sensitivity.DoubleSensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;

/**
 * Sensitivity of derivative price to changes in underlying price.
 *
 * @author ollie
 */
public class Delta extends DoubleSensitivity implements Greek {

    public static final SensitivityId<Delta> ID = SensitivityId.nonSumming("Delta", Delta.class);

    public Delta(final double value) {
        super(value);
    }

    @Override
    public SensitivityId<Delta> sensitivityId() {
        return ID;
    }

}
