package net.ollie.meerkat.calculate.price;

import java.util.Optional;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.meerkat.money.currency.Currency;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;

/**
 *
 * @author ollie
 */
public interface ShiftableSecurityPrice<C extends Currency> extends SecurityPrice<C> {

    @Nonnull
    @CheckReturnValue
    Optional<? extends ShiftableSecurityPrice<C>> shift(@Nonnull SecurityShifts shifts);

}
