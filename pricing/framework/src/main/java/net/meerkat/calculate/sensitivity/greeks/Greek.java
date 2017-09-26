package net.meerkat.calculate.sensitivity.greeks;

import net.meerkat.calculate.sensitivity.Sensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;

/**
 *
 * @author Ollie
 */
public interface Greek extends Sensitivity {

    @Override
    SensitivityId<? extends Greek> sensitivityId();

}
