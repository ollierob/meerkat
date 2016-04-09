package net.ollie.meerkat.calculate.price;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface ShiftableSecurityPrice<C extends CurrencyId> extends SecurityPrice<C> {

    @Nonnull
    ShiftableSecurityPrice<C> shift(@Nonnull SecurityShifts shifts) throws SecurityPrice.InvalidShiftTypeException;

}
