package net.meerkat.pricing.fx;

import net.meerkat.instrument.fx.FxDerivative;
import net.meerkat.pricing.InstrumentPricer;

public interface FxDerivativePricer<T, F extends FxDerivative> extends InstrumentPricer<T, F> {

}
