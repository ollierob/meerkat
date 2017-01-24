package net.ollie.meerkat.calculate.price;

import java.util.Optional;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface ShiftableInstrumentPrice<C extends CurrencyId> extends InstrumentPrice<C> {

    @Nonnull
    @CheckReturnValue
    Optional<? extends ShiftableInstrumentPrice<C>> shift(@Nonnull SecurityShifts shifts);

}
