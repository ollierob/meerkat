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
public interface ShiftableSecurityPrice<C extends CurrencyId> extends SecurityPrice<C> {

    @Nonnull
    @CheckReturnValue
    Optional<? extends ShiftableSecurityPrice<C>> shift(@Nonnull SecurityShifts shifts);

}
