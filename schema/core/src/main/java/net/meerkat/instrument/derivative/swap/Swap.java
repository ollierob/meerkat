package net.meerkat.instrument.derivative.swap;

import javax.annotation.Nonnull;

import net.coljate.list.List;
import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.Security;

/**
 *
 * @author Ollie
 */
public interface Swap extends Security, InstrumentDefinition {

    @Nonnull
    List<? extends SwapLeg<?, ?>> legs();

    default boolean isBullet() {
        return this.legs().count() == 1;
    }

}
