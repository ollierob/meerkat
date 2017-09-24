package net.meerkat.instrument.derivative.swap;

import javax.annotation.Nonnull;

import net.coljate.set.Set;
import net.meerkat.instrument.AggregateInstrument;
import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.derivative.Derivative;

/**
 * A combination of at least two swaps.
 *
 * @author ollie
 */
public interface BlendedSwap
        extends InstrumentDefinition, Derivative<AggregateInstrument> {

    @Nonnull
    Set<? extends Swap> swaps();

}
