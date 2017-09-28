package net.meerkat.instrument.derivative.swap;

import java.util.OptionalInt;

import javax.annotation.Nonnull;

import net.coljate.list.List;
import net.meerkat.identifier.currency.HasCurrencyIds;

/**
 *
 * @author ollie
 */
public interface SwapLegs<S extends SwapLeg<?, ?>> extends HasCurrencyIds {

    @Nonnull
    OptionalInt numLegs();

    @SafeVarargs
    static <S extends SwapLeg<?, ?>> SwapLegs.Finite<S> of(final S... legs) {
        throw new UnsupportedOperationException(); //TODO
    }

    interface Finite<S extends SwapLeg<?, ?>> extends SwapLegs<S>, List<S> {

    }

}
