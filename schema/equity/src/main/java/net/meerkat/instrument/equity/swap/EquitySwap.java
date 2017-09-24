package net.meerkat.instrument.equity.swap;

import javax.annotation.Nonnull;

import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.derivative.swap.Swap;
import net.meerkat.instrument.equity.EquityDerivative;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public interface EquitySwap<E extends Instrument> extends EquityDerivative<E>, Swap {

    @Nonnull
    Money<?> notional();

}
