package net.ollie.meerkat.calculate.price.shifts;

import javax.annotation.Nonnull;

import net.meerkat.money.currency.Currency;
import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
public interface PriceShifts extends SecurityShifts {

    @Nonnull
    <C extends Currency> Money<C> shift(@Nonnull Money<C> price);

}
