package net.ollie.meerkat.calculate.price;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.ollie.goat.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface ShiftableSecurityPrice<C extends CurrencyId> extends SecurityPrice<C> {

    @Nonnull
    @CheckReturnValue
    ShiftableSecurityPrice<C> shift(@Nonnull SecurityShifts shifts) throws SecurityPrice.InvalidShiftTypeException;

}
