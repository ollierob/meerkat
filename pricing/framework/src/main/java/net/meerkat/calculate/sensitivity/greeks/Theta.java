package net.meerkat.calculate.sensitivity.greeks;

import net.meerkat.calculate.sensitivity.SensitivityId;

/**
 *
 * @author ollie
 */
public class Theta implements Greek {

    public static final SensitivityId<Theta> ID = SensitivityId.nonSumming("Theta", Theta.class);

    @Override
    public SensitivityId<Theta> sensitivityId() {
        return ID;
    }

}
