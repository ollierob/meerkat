package net.meerkat.instrument.derivative.swap;

import net.coljate.list.List;
import net.meerkat.identifier.currency.HasCurrencyIds;
import net.meerkat.time.calendar.TemporalSequence;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.OptionalInt;
import java.util.function.Predicate;

/**
 * @author ollie
 */
public interface SwapLegs<S extends SwapLeg<?, ?>> extends TemporalSequence<S>, HasCurrencyIds {

    @Nonnull
    OptionalInt numLegs();

    @Nonnull
    @Override
    SwapLegs<S> filter(Predicate<? super S> predicate);

    @SafeVarargs
    static <S extends SwapLeg<?, ?>> SwapLegs.Finite<S> of(final S... legs) {
        throw new UnsupportedOperationException(); //TODO
    }

    interface Finite<S extends SwapLeg<?, ?>> extends SwapLegs<S>, List<S> {

        @Nonnull
        @Override
        Finite<S> filter(Predicate<? super S> predicate);

        default List<S> deliveredOnOrAfter(final LocalDate date) {
            return this.filter(leg -> !date.isBefore(leg.deliveryDate()));
        }

    }

}
