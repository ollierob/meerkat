package net.meerkat.calculate.price;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.meerkat.calculate.price.shifts.SecurityShifts;
import net.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface ShiftablePrice<C extends CurrencyId>
        extends Price<C> {

    @Nonnull
    @CheckReturnValue
    ShiftablePrice<C> shift(@Nonnull SecurityShifts shifts);

}
