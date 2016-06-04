package net.ollie.meerkat.calculate.price;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.goat.currency.Currency;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;

/**
 *
 * @author ollie
 */
public interface ShiftableSecurityPrice<C extends Currency> extends SecurityPrice<C> {

    @Nonnull
    @CheckReturnValue
    ShiftableSecurityPrice<C> shift(@Nonnull SecurityShifts shifts) throws SecurityPrice.InvalidShiftTypeException;

}
