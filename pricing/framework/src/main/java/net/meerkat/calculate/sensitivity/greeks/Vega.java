package net.meerkat.calculate.sensitivity.greeks;

import net.meerkat.calculate.sensitivity.DoubleSensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;

/**
 *
 * @author ollie
 */
public class Vega extends DoubleSensitivity implements Greek {
    
    public static final SensitivityId<Vega> ID = SensitivityId.nonSumming("Vega", Vega.class);

    public Vega(final double value) {
        super(value);
    }

    @Override
    public SensitivityId<? extends Greek> sensitivityId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
