package net.meerkat.calculate.price;

import java.util.Optional;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.meerkat.calculate.price.shifts.SecurityShifts;
import net.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface ShiftableInstrumentPrice<C extends CurrencyId> extends InstrumentPrice<C> {

    @Nonnull
    @CheckReturnValue
    Optional<? extends ShiftableInstrumentPrice<C>> shift(@Nonnull SecurityShifts shifts);

}
