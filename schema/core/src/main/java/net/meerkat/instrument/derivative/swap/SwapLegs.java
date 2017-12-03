package net.meerkat.instrument.derivative.swap;

import net.coljate.list.List;
import net.coljate.sequence.Sequence;
import net.meerkat.identifier.currency.HasCurrencyIds;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.OptionalInt;

/**
 *
 * @author ollie
 */
public interface SwapLegs<S extends SwapLeg<?, ?>> extends Sequence<S>, HasCurrencyIds {

    @Nonnull
    OptionalInt numLegs();

    @SafeVarargs
    static <S extends SwapLeg<?, ?>> SwapLegs.Finite<S> of(final S... legs) {
        throw new UnsupportedOperationException(); //TODO
    }

    interface Finite<S extends SwapLeg<?, ?>> extends SwapLegs<S>, List<S> {

        default List<S> deliveredOnOrAfter(final LocalDate date) {
            return this.filter(leg -> !date.isBefore(leg.deliveryDate()));
        }

    }

}
