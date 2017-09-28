package net.meerkat.instrument.fx.swap;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.derivative.swap.Swap;
import net.meerkat.instrument.fx.FxDerivative;

/**
 *
 * @author ollie
 */
public interface PerpetualFxSwap<P extends CurrencyId, R extends CurrencyId>
        extends Swap, FxDerivative {

}
