package net.ollie.meerkat.calculate.price.shifts;

import javax.annotation.Nonnull;

import net.ollie.goat.currency.CurrencyId;
import net.ollie.goat.money.Money;

/**
 *
 * @author Ollie
 */
public interface PriceShifts extends SecurityShifts {

    @Nonnull
    <C extends CurrencyId> Money<C> shift(@Nonnull Money<C> price);

}
