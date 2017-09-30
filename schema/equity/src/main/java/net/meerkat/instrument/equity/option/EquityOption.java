package net.meerkat.instrument.equity.option;

import net.meerkat.instrument.derivative.option.Option;
import net.meerkat.instrument.equity.Equity;
import net.meerkat.instrument.equity.EquityDerivative;

/**
 *
 * @author Ollie
 */
public interface EquityOption<E extends Equity>
        extends EquityDerivative<E>, Option<E> {

}
